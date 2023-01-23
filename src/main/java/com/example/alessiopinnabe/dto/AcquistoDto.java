package com.example.alessiopinnabe.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AcquistoDto {
    private Integer id;
    private UtenteDto utente;
    private ProdottoDto prodotto;
    private Integer quantita;
    private Timestamp dataAcquisto;
    private boolean fromDetail;
    private DatiEventoDto datiEvento;
}
