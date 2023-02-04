package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ObjectDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UtenteDto extends ObjectDTO {
    private String anagrafica;
    private String email;
    private String provider;
    private String photoUrl;
    private DominioDto tipoUtente;
    private Integer nAcquistiProdotti;
    private Integer nAcquistiEventi;
    private List<TokenDto> tokens;

}
