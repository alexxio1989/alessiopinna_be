package com.example.alessiopinnabe.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.Utente;
import com.example.alessiopinnabe.mapper.mapstruct.UtenteMapper;
import com.example.alessiopinnabe.repositories.UtenteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AuthorizationFilter extends BasicAuthenticationFilter {


    private final UtenteRepository userRepository;
    private final ObjectMapper mapper;
    @Autowired
    private UtenteMapper utenteMapper;

    @Value("${security.prefix}")
    private String prefix;

    public AuthorizationFilter(AuthenticationManager authenticationManager, UtenteRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String header = request.getHeader(JwtProvider.headerParam);
        if (header != null && header.startsWith(JwtProvider.prefix)) {
            final DecodedJWT decoded = JwtProvider.verifyJwt(header.replace(JwtProvider.prefix, ""));
            final ObjectNode userNode = this.mapper.readValue(decoded.getClaim("user").asString(), ObjectNode.class);
            final UtenteDto user = this.mapper.convertValue(userNode, UtenteDto.class);
            /*this.userRepository.findById(user.getId()).ifPresent(entity -> {
                UtenteDto dto = utenteMapper.getDto(entity);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(dto, null, dto.getAuthorities()));
            });*/
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));

        }
        chain.doFilter(request, response);
    }
}
