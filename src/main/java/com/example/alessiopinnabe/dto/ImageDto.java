package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ObjectDTO;
import com.example.alessiopinnabe.entity.Servizio;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ImageDto extends ObjectDTO {

    private String key;
    private String imgUrl;
    private byte[] img;

}
