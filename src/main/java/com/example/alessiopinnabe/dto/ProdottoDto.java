package com.example.alessiopinnabe.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ProdottoDto {
    private Integer id;
    private String nome;
    private String nomeExt;
    private String descrizione;
    private String imgName;
    private DominioDto tipo;
    private String giorniOrari;
    private BigDecimal prezzo;
    private Date dataCreazione;
}
