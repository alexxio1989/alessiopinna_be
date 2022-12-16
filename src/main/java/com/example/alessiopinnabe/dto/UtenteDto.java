package com.example.alessiopinnabe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UtenteDto {
    private Integer id;
    private String username;
    private String skypeID;
    private String email;
    private DominioDto tipo;
    private List<PrenotazioneDto> prenotazioni;
}