package com.example.alessiopinnabe.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class DatiEventoDto {
    private Integer id;
    private String idEvent;
    private Timestamp dataInizio;
    private Timestamp dataFine;
}
