package com.example.alessiopinnabe.config;

import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.dto.core.ResponseCore;
import com.example.alessiopinnabe.entity.Token;
import com.example.alessiopinnabe.entity.Utente;
import com.example.alessiopinnabe.exceptions.CoreException;
import com.example.alessiopinnabe.handlers.ExceptionsHandler;
import com.example.alessiopinnabe.mapper.mapstruct.UtenteMapper;
import com.example.alessiopinnabe.repositories.UtenteRepository;
import com.example.alessiopinnabe.util.Constants;
import com.example.alessiopinnabe.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorizationFilter extends OncePerRequestFilter {


    @Autowired
    private UtenteRepository userRepository;
    @Autowired
    private UtenteMapper utenteMapper;

    @Value("${security.prefix}")
    private String prefix;
    @Autowired
    private ExceptionsHandler exceptionsHandler;



    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String id = request.getHeader("ID_UTENTE");
        if(StringUtils.isNotEmpty(id)){
            Optional<Utente> byId = this.userRepository.findById(id);
            if(byId.isPresent()){
                Utente entity = byId.get();
                UtenteDto dto = utenteMapper.getDtoLight(entity);
                List<Token> tokensEntities = entity.getTokens();
                Token tokenGoogle = null;
                Token tokenDefault = null;
                boolean isTokenGoogleExpired = true ;
                boolean isTokenDefaultExpired = true ;
                if(CollectionUtils.isNotEmpty(tokensEntities)){
                    Optional<Token> optTokenDefault = tokensEntities.stream().filter(t -> Constants.DEFAULT.equalsIgnoreCase(t.getProvider())).findFirst();
                    if(optTokenDefault.isPresent()){
                        tokenDefault = optTokenDefault.get();
                    }
                    Optional<Token> optTokenGoogle = tokensEntities.stream().filter(t -> Constants.GOOGLE.equalsIgnoreCase(t.getProvider())).findFirst();
                    if(optTokenGoogle.isPresent()){
                        tokenGoogle = optTokenGoogle.get();
                    }
                    isTokenGoogleExpired = Constants.GOOGLE.equalsIgnoreCase(entity.getProvider()) ? tokenGoogle != null ? Util.isTmspExpired(tokenGoogle.getDateExiration()) : true : false;
                    isTokenDefaultExpired = tokenDefault != null ? Util.isTmspExpired(tokenDefault.getDateExiration()) : true;
                }
                if(CollectionUtils.isEmpty(tokensEntities) ||
                        isTokenGoogleExpired ||
                        isTokenDefaultExpired
                ){
                    ResponseEntity<ResponseCore> responseCustom = exceptionsHandler.handleRequestException(request, new CoreException("Token scaduto", HttpStatus.UNAUTHORIZED, null));
                    mapErrorResponse(response, responseCustom);
                    return;
                } else {
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(dto, null, dto.getAuthorities()));
                }
            }

        }
        chain.doFilter(request, response);
    }

    private void mapErrorResponse(HttpServletResponse response, ResponseEntity<ResponseCore> responseCustom) throws IOException {
        ResponseCore body = responseCustom.getBody();
        response.setStatus(body .getCode());
        response.setContentType("application/json");

        //pass down the actual obj that exception handler normally send
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.print(mapper.writeValueAsString(body ));
        out.flush();
    }
}
