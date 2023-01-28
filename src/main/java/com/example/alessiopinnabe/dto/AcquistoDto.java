package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ObjectDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AcquistoDto extends ObjectDTO {
    private UtenteDto utente;
    private ProdottoDto prodotto;
    private Integer quantita;
    private Timestamp dataAcquisto;
    private boolean fromDetail;
    private EventoDto evento;
    private DettaglioAcquistoDto dettaglioAcquistoDto;
    private boolean isEvento;
}
