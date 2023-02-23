package com.example.alessiopinnabe.dto.response;

import com.example.alessiopinnabe.dto.EventoDto;
import com.example.alessiopinnabe.dto.ProdottoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseServiziDto {
    private List<ProdottoDto> prodotti;
    private List<EventoDto> eventi;
}
