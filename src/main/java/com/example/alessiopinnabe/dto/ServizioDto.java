package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ObjectDTO;
import com.example.alessiopinnabe.entity.ImgServizio;
import com.example.alessiopinnabe.entity.TplServizio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

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
    private List<ImgServizio> images = new ArrayList<>();

}
