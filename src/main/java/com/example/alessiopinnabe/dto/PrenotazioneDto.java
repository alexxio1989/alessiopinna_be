package com.example.alessiopinnabe.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class PrenotazioneDto {
    private Long id;
    private String idEvent;
    private UtenteDto utente;
    private CorsoDto corso;
    private BigDecimal qntOre;
    private Timestamp dataPrenotazione;
    private boolean fromDetail;
}
