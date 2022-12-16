package com.example.alessiopinnabe.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class PrenotazioneDto {
    private CorsoDto corso;
    private BigDecimal qntOre;
    private Date dataPrenotazione;
    private String oraInizio;
}
