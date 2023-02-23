package com.example.alessiopinnabe.dto.response;

import com.example.alessiopinnabe.dto.AcquistoEventoDto;
import com.example.alessiopinnabe.dto.AcquistoProdottoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseAcquistoDto {
    private List<AcquistoProdottoDto> acquistoProdotti;
    private List<AcquistoEventoDto> acquistoEventi;
}
