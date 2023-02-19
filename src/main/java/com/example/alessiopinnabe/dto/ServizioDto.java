package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ObjectDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ServizioDto extends ObjectDTO{
    private String nome;
    private String nomeExt;
    private String descrizione;
    private Date dataCreazione;
    private Integer enable;
    private BigDecimal prezzo;
    private DominioDto tipoServizio;
    private List<ImageDto> images = new ArrayList<>();

}
