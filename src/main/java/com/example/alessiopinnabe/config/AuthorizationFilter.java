package com.example.alessiopinnabe.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.alessiopinnabe.dto.TokenDto;
import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.dto.response.ErrorResponse;
import com.example.alessiopinnabe.dto.response.ResponseAcquistoDto;
import com.example.alessiopinnabe.entity.Token;
import com.example.alessiopinnabe.entity.Utente;
import com.example.alessiopinnabe.exceptions.CustomExceptions;
import com.example.alessiopinnabe.mapper.mapstruct.UtenteMapper;
import com.example.alessiopinnabe.repositories.UtenteRepository;
import com.example.alessiopinnabe.util.Constants;
import com.example.alessiopinnabe.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorizationFilter extends OncePerRequestFilter {


    @Autowired
    private UtenteRepository userRepository;
    @Autowired
    private UtenteMapper utenteMapper;

    @Value("${security.prefix}")
    private String prefix;



    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        final String id = request.getHeader("ID_UTENTE");
        if(StringUtils.isNotEmpty(id)){
            this.userRepository.findById(id).ifPresent(entity -> {
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
                    isTokenGoogleExpired = tokenGoogle != null ? Util.isTmspExpired(tokenGoogle.getDateExiration()) : true;
                    isTokenDefaultExpired = tokenDefault != null ? Util.isTmspExpired(tokenDefault.getDateExiration()) : true;
                }
                if(CollectionUtils.isEmpty(tokensEntities) ||
                        tokenGoogle == null ||
                        tokenDefault == null ||
                        isTokenGoogleExpired ||
                        isTokenDefaultExpired
                ){
                    response.setStatus(999);
                } else {
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(dto, null, dto.getAuthorities()));
                }
            });
        }
        chain.doFilter(request, response);
    }

    /*private void handleError(HttpServletResponse response, Integer error , String errorDescription) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(error);
        errorResponse.setErrorDescription(errorDescription);
        response.setContentType("application/json");
        response.setStatus(500);
        String s = mapper.writeValueAsString(errorResponse);
        response.getOutputStream().println(s);
    }*/
}
