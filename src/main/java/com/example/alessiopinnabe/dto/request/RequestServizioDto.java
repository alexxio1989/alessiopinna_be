package com.example.alessiopinnabe.dto.request;

import com.example.alessiopinnabe.dto.ServizioDto;
import com.example.alessiopinnabe.dto.UtenteDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestServizioDto {
    private UtenteDto utente;
    private ServizioDto servizioSelected;
}
