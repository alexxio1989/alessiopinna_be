package com.example.alessiopinnabe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UtenteDto {
    private Integer id;
    private String name;
    private String skypeID;
    private String email;
    private DominioDto tipo;
    private String photoUrl;
    private List<AcquistoDto> acquisti;
    private List<TokenResponseDto> tokens;
    private String provider;
}
