package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.*;
import com.example.alessiopinnabe.service.ServiceCorso;
import com.example.alessiopinnabe.service.ServiceUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("utente")
@CrossOrigin(origins = "*")
public class UtenteController {

    @Autowired
    private ServiceUtente serviceCorso;


    @PostMapping("/signin")
    @CrossOrigin(origins = "*")
    public ResponseUtenteDto signin(@RequestBody RequestLoginDto req) {
        return serviceCorso.signin(req);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseUtenteDto silogingnin(@RequestBody RequestLoginDto req) {
        return serviceCorso.login(req);
    }

}
