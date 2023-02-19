package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.components.GoogleManager;
import com.example.alessiopinnabe.dto.UtenteDtoFull;
import com.example.alessiopinnabe.exceptions.CoreException;
import com.example.alessiopinnabe.service.GoogleService;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.oauth2.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("google")
public class GoogleController {

    @Autowired
    private GoogleManager googleManager;
    @Autowired
    private GoogleService googleService;
    @Value("${fe.path}")
    private String fePath;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public RedirectView googleConnectionStatus(HttpServletRequest request) {
        String authorize = null;
        try {
            authorize = googleManager.authorize();
        } catch (Exception ex) {
            throw new CoreException("Errore durante l'autorizzazione google", HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
        }
        return new RedirectView(authorize);
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET, params = "code")
    public RedirectView oauth2Callback(@RequestParam(value = "code") String code) throws IOException {
        com.google.api.services.calendar.model.Events eventList;
        String message;
        Userinfo userInfo = null;
        try {
            TokenResponse token = googleManager.newToken(code);
            userInfo = googleManager.getUserinfo(googleManager.getCredential(token));
            UtenteDtoFull utenteFull = googleService.loginFromGoogle(userInfo,token);
            return new RedirectView(fePath + "/?email=" + utenteFull.getUtente().getEmail() + "&id=" +utenteFull.getPassword());
        } catch (Exception e) {
            throw e;
        }
    }
}
