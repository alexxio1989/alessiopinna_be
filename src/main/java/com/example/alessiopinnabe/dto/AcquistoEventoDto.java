package com.example.alessiopinnabe.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AcquistoEventoDto extends AcquistoDto{
    private EventoDto evento;
    private Timestamp dataInizio;
    private Timestamp dataFine;
    private String idEventCalendar;
}
