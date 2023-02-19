package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.config.JwtProvider;
import com.example.alessiopinnabe.dto.UtenteDtoFull;
import com.example.alessiopinnabe.dto.request.RequestLoginDto;
import com.example.alessiopinnabe.dto.response.ResponseUtenteDto;
import com.example.alessiopinnabe.components.GoogleManager;
import com.example.alessiopinnabe.service.ServiceUtente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.oauth2.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/signin")
    public ResponseEntity<ResponseUtenteDto> signin(@RequestBody RequestLoginDto req) {
        return serviceUtente.save(req,null);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseUtenteDto> login(@RequestBody RequestLoginDto req) {
        return serviceUtente.get(req,null);
    }

    @PostMapping("/login/admin")
    public ResponseEntity<ResponseUtenteDto> loginAdmin(@RequestBody RequestLoginDto req) {
        return serviceUtente.loginAdmin(req);
    }




}
