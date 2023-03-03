package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ObjectDTO;
import com.example.alessiopinnabe.entity.Servizio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class ImageDto extends ObjectDTO {
    private String base64;
    private String url;
}
