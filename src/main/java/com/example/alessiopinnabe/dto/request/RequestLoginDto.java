package com.example.alessiopinnabe.dto.request;

import com.example.alessiopinnabe.dto.UtenteDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLoginDto {
    private UtenteDto utente;
    private String password;
}
