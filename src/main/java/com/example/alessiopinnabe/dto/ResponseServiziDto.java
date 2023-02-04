package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ResponseCore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseServiziDto extends ResponseCore {
    private List<ProdottoDto> prodotti;
    private List<EventoDto> eventi;
}
