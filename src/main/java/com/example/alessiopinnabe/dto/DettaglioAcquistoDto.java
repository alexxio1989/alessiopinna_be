package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ObjectDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class DettaglioAcquistoDto extends ObjectDTO {
    private String keyPayment;
    private String type;
}
