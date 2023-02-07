package com.example.alessiopinnabe.dto.response;

import com.example.alessiopinnabe.dto.EventoDto;
import com.example.alessiopinnabe.dto.ProdottoDto;
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
