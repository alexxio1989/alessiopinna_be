package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ResponseCore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseCorsoDto extends ResponseCore {
    private List<CorsoDto> corsi;
}
