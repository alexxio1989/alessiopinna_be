package com.example.alessiopinnabe.dto;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
public class TokenResponse {
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
