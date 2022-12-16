package com.example.alessiopinnabe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLoginDto {
    private UtenteDto utente;
    private String password;
}
