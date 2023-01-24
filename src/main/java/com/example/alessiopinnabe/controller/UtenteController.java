package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.*;
import com.example.alessiopinnabe.service.ServiceUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("utente")
public class UtenteController {

    @Autowired
    private ServiceUtente serviceUtente;

    @PostMapping("/signin")
    public ResponseUtenteDto signin(@RequestBody RequestLoginDto req) {
        return serviceUtente.signin(req);
    }

    @PostMapping("/login")
    public ResponseUtenteDto login(@RequestBody RequestLoginDto req) {
        return serviceUtente.login(req);
    }

}
