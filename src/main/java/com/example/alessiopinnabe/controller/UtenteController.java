package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.config.JwtProvider;
import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.dto.UtenteDtoFull;
import com.example.alessiopinnabe.dto.request.RequestLoginDto;
import com.example.alessiopinnabe.dto.response.ResponseUtenteDto;
import com.example.alessiopinnabe.entity.Utente;
import com.example.alessiopinnabe.service.ServiceGoogle;
import com.example.alessiopinnabe.service.ServiceUtente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.oauth2.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UtenteController {

    @Autowired
    private ServiceUtente serviceUtente;
    @Autowired
    private ServiceGoogle googleService;
    @Value("${fe.path}")
    private String fePath;

    @PostMapping("/signin")
    public ResponseUtenteDto signin(@RequestBody RequestLoginDto req) {
        return serviceUtente.signin(req);
    }

    @PostMapping("/login")
    public ResponseUtenteDto login(@RequestBody RequestLoginDto req) {
        return serviceUtente.login(req);
    }

    @RequestMapping(value = "/login/google", method = RequestMethod.GET)
    public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {
        return new RedirectView(googleService.authorize());
    }

    @RequestMapping(value = "/login/google", method = RequestMethod.GET, params = "code")
    public RedirectView oauth2Callback(@RequestParam(value = "code") String code) throws IOException {
        com.google.api.services.calendar.model.Events eventList;
        String message;
        Userinfo userInfo = null;
        try {
            TokenResponse token = googleService.newToken(code);
            userInfo = googleService.getUserinfo(googleService.getCredential(token));
            UtenteDtoFull utente = serviceUtente.loginFromGoogle(userInfo,token);
            String tokenJWT = generateJWT(utente);
            return new RedirectView(fePath + "/?email=" + utente.getUtente().getEmail() + "&id=" +utente.getPassword() + "&token=" + tokenJWT);
        } catch (Exception e) {
            throw e;
        }
    }

    private String generateJWT(UtenteDtoFull utente){
        ObjectNode userNode = new ObjectMapper().convertValue(utente.getUtente(), ObjectNode.class);
        userNode.remove("password");
        Map claimMap = new HashMap(0);
        claimMap.put("user", userNode);
        return JwtProvider.createJwt(utente.getUtente().getEmail(), claimMap);
    }

}
