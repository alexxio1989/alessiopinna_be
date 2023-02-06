package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.TokenDto;
import com.example.alessiopinnabe.entity.Token;
import com.example.alessiopinnabe.entity.Utente;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;

public class TokenMapper {

    public static TokenDto fromEntityToDto(Token entity){
        TokenDto out = new TokenDto();
        out.setId(entity.getId());
        out.setAccessToken(entity.getAccessToken());
        //out.setRefreshToken(entity.getRefreshToken());
        out.setTokenType(entity.getTokenType());
        out.setScope(entity.getScope());
        out.setExpiresInSeconds(new Long(entity.getExpiresInSeconds()));
        out.setDateCreation(entity.getDateCreation());
        out.setDateExiration(entity.getDateExiration());
        out.setProvider(entity.getProvider());
        return out;
    }

    public static Token fromDtoToEntity(TokenDto tokenResponseDto, Utente utenteEntity){
        Token out = new Token();
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

    public static com.google.api.client.auth.oauth2.TokenResponse fromEntityToGoogle(Token entity){
        com.google.api.client.auth.oauth2.TokenResponse out = new com.google.api.client.auth.oauth2.TokenResponse();
        out.setAccessToken(entity.getAccessToken());
        out.setTokenType(entity.getTokenType());
        out.setScope(entity.getScope());
        out.setExpiresInSeconds(new Long(entity.getExpiresInSeconds()));
        return out;
    }

    public static com.google.api.client.auth.oauth2.TokenResponse fromDtoToGoogle(TokenDto tokenResponseDto){
        com.google.api.client.auth.oauth2.TokenResponse out = new com.google.api.client.auth.oauth2.TokenResponse();
        out.setAccessToken(tokenResponseDto.getAccessToken());
        out.setRefreshToken(tokenResponseDto.getRefreshToken());
        out.setTokenType(tokenResponseDto.getTokenType());
        out.setScope(tokenResponseDto.getScope());
        out.setExpiresInSeconds(tokenResponseDto.getExpiresInSeconds());
        return out;
    }

    public static TokenDto fromGoogleToDto(com.google.api.client.auth.oauth2.TokenResponse tokenResponse){
        TokenDto out = new TokenDto();
        out.setAccessToken(tokenResponse.getAccessToken());
        out.setRefreshToken(tokenResponse.getRefreshToken());
        out.setTokenType(tokenResponse.getTokenType());
        out.setScope(tokenResponse.getScope());
        out.setExpiresInSeconds(tokenResponse.getExpiresInSeconds());
        return out;
    }

    public static Token fromGoogleToEntity(com.google.api.client.auth.oauth2.TokenResponse tokenResponse , Utente utenteEntity , String id){
        Token out = new Token();
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

    public static Token updateEntity(Token old , com.google.api.client.auth.oauth2.TokenResponse refreshedToken){
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
