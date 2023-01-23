package com.example.alessiopinnabe.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TokenResponseDto {
    private Integer id;
    private String accessToken;
    private String tokenType;
    private Long expiresInSeconds;
    private String refreshToken;
    private String scope;
    private Timestamp dateCreation;
    private Timestamp dateExiration;
    private String provider;
}
