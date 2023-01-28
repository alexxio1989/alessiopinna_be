package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ObjectDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UtenteDto extends ObjectDTO {
    private String name;
    private String skypeID;
    private String email;
    private DominioDto tipo;
    private String photoUrl;
    private List<AcquistoDto> acquisti;
    private List<TokenResponseDto> tokens;
    private String provider;
}
