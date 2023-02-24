package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ObjectDTO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME , include = JsonTypeInfo.As.EXISTING_PROPERTY , property = "type" , visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProdottoDto.class , name = "PRODOTTO"),
        @JsonSubTypes.Type(value = EventoDto.class , name = "EVENTO")
})
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ServizioDto extends ObjectDTO{
    private TypeServizio type;
    private String nome;
    private String nomeExt;
    private String descrizione;
    private Date dataCreazione;
    private Integer enable;
    private BigDecimal prezzo;
    private DominioDto tipoServizio;
    private List<ImageDto> images = new ArrayList<>();

}
