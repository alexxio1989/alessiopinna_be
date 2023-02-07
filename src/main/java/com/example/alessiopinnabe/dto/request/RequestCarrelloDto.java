package com.example.alessiopinnabe.dto.request;

import com.example.alessiopinnabe.dto.AcquistoEventoDto;
import com.example.alessiopinnabe.dto.AcquistoProdottoDto;
import com.example.alessiopinnabe.dto.UtenteDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestCarrelloDto {
    private UtenteDto utente;
    private List<AcquistoProdottoDto> acquistoProdotti;
    private List<AcquistoEventoDto> acquistoEventi;
}
