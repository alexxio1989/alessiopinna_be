package com.example.alessiopinnabe.dto.request;

import com.example.alessiopinnabe.dto.UtenteDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestLoginDto {
    @NotNull private UtenteDto utente;
    @NotNull private String password;
}
