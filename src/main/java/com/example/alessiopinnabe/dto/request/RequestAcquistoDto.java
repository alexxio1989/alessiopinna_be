package com.example.alessiopinnabe.dto.request;

import com.example.alessiopinnabe.dto.AcquistoDto;
import com.example.alessiopinnabe.dto.AcquistoEventoDto;
import com.example.alessiopinnabe.dto.AcquistoProdottoDto;
import com.example.alessiopinnabe.dto.UtenteDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestAcquistoDto {
    private UtenteDto utente;
    private AcquistoDto acquistoSelected;
    private List<AcquistoDto> acquisti;
}
