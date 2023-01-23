package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.TokenResponseDto;
import com.example.alessiopinnabe.entity.TokenEntity;
import com.example.alessiopinnabe.entity.UtenteEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;

public class TokenMapper {

    public static TokenResponseDto fromEntityToDto(TokenEntity Entity){
        TokenResponseDto out = new TokenResponseDto();
        out.setId(Entity.getId());
        out.setAccessToken(Entity.getAccessToken());
        //out.setRefreshToken(Entity.getRefreshToken());
        out.setTokenType(Entity.getTokenType());
        out.setScope(Entity.getScope());
        out.setExpiresInSeconds(new Long(Entity.getExpiresInSeconds()));
        out.setDateCreation(Entity.getDateCreation());
        out.setDateExiration(Entity.getDateExiration());
        out.setProvider(Entity.getProvider());
        return out;
    }

    public static TokenEntity fromDtoToEntity(TokenResponseDto tokenResponseDto, UtenteEntity utenteEntity){
        TokenEntity out = new TokenEntity();
        out.setId(tokenResponseDto.getId());
        out.setAccessToken(tokenResponseDto.getAccessToken());
        //out.setRefreshToken(Entity.getRefreshToken());
        out.setTokenType(tokenResponseDto.getTokenType());
        out.setScope(tokenResponseDto.getScope());
        out.setExpiresInSeconds(tokenResponseDto.getExpiresInSeconds().intValue());
        out.setUtente(utenteEntity);
        out.setDateCreation(Timestamp.from(Instant.now()));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, tokenResponseDto.getExpiresInSeconds().intValue());
        Timestamp later = new Timestamp(cal.getTime().getTime());
        out.setDateExiration(later);
        out.setProvider(tokenResponseDto.getProvider());
        return out;
    }

    public static com.google.api.client.auth.oauth2.TokenResponse fromEntityToGoogle(TokenEntity Entity){
        com.google.api.client.auth.oauth2.TokenResponse out = new com.google.api.client.auth.oauth2.TokenResponse();
        out.setAccessToken(Entity.getAccessToken());
        out.setTokenType(Entity.getTokenType());
        out.setScope(Entity.getScope());
        out.setExpiresInSeconds(new Long(Entity.getExpiresInSeconds()));
        return out;
    }

    public static com.google.api.client.auth.oauth2.TokenResponse fromDtoToGoogle(TokenResponseDto tokenResponseDto){
        com.google.api.client.auth.oauth2.TokenResponse out = new com.google.api.client.auth.oauth2.TokenResponse();
        out.setAccessToken(tokenResponseDto.getAccessToken());
        out.setRefreshToken(tokenResponseDto.getRefreshToken());
        out.setTokenType(tokenResponseDto.getTokenType());
        out.setScope(tokenResponseDto.getScope());
        out.setExpiresInSeconds(tokenResponseDto.getExpiresInSeconds());
        return out;
    }

    public static TokenResponseDto fromGoogleToDto(com.google.api.client.auth.oauth2.TokenResponse tokenResponse){
        TokenResponseDto out = new TokenResponseDto();
        out.setAccessToken(tokenResponse.getAccessToken());
        out.setRefreshToken(tokenResponse.getRefreshToken());
        out.setTokenType(tokenResponse.getTokenType());
        out.setScope(tokenResponse.getScope());
        out.setExpiresInSeconds(tokenResponse.getExpiresInSeconds());
        return out;
    }

    public static TokenEntity fromGoogleToEntity(com.google.api.client.auth.oauth2.TokenResponse tokenResponse , UtenteEntity utenteEntity , Integer id){
        TokenEntity out = new TokenEntity();
        if(id != null){
            out.setId(id);
        }
        out.setAccessToken(tokenResponse.getAccessToken());
        out.setTokenType(tokenResponse.getTokenType());
        out.setScope(tokenResponse.getScope());
        out.setExpiresInSeconds(tokenResponse.getExpiresInSeconds().intValue());
        out.setUtente(utenteEntity);
        out.setDateCreation(Timestamp.from(Instant.now()));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, tokenResponse.getExpiresInSeconds().intValue());
        Timestamp later = new Timestamp(cal.getTime().getTime());
        out.setDateExiration(later);
        out.setProvider("GOOGLE");
        return out;
    }

    public static TokenEntity updateEntity(TokenEntity old , com.google.api.client.auth.oauth2.TokenResponse refreshedToken){
        old.setAccessToken(refreshedToken.getAccessToken());
        old.setTokenType(refreshedToken.getTokenType());
        old.setScope(refreshedToken.getScope());
        old.setExpiresInSeconds(refreshedToken.getExpiresInSeconds().intValue());
        old.setDateCreation(Timestamp.from(Instant.now()));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, refreshedToken.getExpiresInSeconds().intValue());
        Timestamp later = new Timestamp(cal.getTime().getTime());
        old.setDateExiration(later);
        return old;
    }
}
