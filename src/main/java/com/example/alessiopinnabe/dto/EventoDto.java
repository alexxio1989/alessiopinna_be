package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ObjectDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EventoDto extends ServizioDto {
    private Timestamp dataInizio;
    private Timestamp dataFine;
}
