package com.example.alessiopinnabe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLogin {
    private Utente utente;
    private String password;
}
