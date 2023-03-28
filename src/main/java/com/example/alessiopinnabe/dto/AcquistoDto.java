package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ObjectDTO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME , include = JsonTypeInfo.As.EXISTING_PROPERTY , property = "type" , visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AcquistoProdottoDto.class , name = "ACQUISTO_PRODOTTO"),
        @JsonSubTypes.Type(value = AcquistoEventoDto.class , name = "ACQUISTO_EVENTO")
})
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AcquistoDto extends ObjectDTO {
    private TypeAcquisto type;
    private Integer quantita;
    private Timestamp dataAcquisto;
    private UtenteDto utente;
    private boolean fromDetail;
}
