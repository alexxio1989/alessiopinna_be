package com.example.alessiopinnabe.dto;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {
    @Key("access_token")
    private String accessToken;
    @Key("token_type")
    private String tokenType;
    @Key("expires_in")
    private Long expiresInSeconds;
    @Key("refresh_token")
    private String refreshToken;
    @Key
    private String scope;
}
