package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ObjectDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TokenDto extends ObjectDTO {
    private String accessToken;
    private String tokenType;
    private Long expiresInSeconds;
    private String scope;
    private Timestamp dateCreation;
    private Timestamp dateExiration;
    private String refreshToken;
    private String provider;
}
