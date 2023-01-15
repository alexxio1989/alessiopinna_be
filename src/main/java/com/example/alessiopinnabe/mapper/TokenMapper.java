package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.TokenResponse;
import com.example.alessiopinnabe.entity.UserTokenEntity;
import com.example.alessiopinnabe.entity.UtenteEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;

public class TokenMapper {

    public static TokenResponse fromEntityToDto(UserTokenEntity Entity){
        TokenResponse out = new TokenResponse();
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

    public static UserTokenEntity fromDtoToEntity(TokenResponse tokenResponse , UtenteEntity utenteEntity){
        UserTokenEntity out = new UserTokenEntity();
        out.setId(tokenResponse.getId());
        out.setAccessToken(tokenResponse.getAccessToken());
        //out.setRefreshToken(Entity.getRefreshToken());
        out.setTokenType(tokenResponse.getTokenType());
        out.setScope(tokenResponse.getScope());
        out.setExpiresInSeconds(tokenResponse.getExpiresInSeconds().intValue());
        out.setUtenteIdutente(utenteEntity);
        out.setDateCreation(Timestamp.from(Instant.now()));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, tokenResponse.getExpiresInSeconds().intValue());
        Timestamp later = new Timestamp(cal.getTime().getTime());
        out.setDateExiration(later);
        out.setProvider(tokenResponse.getProvider());
        return out;
    }

    public static com.google.api.client.auth.oauth2.TokenResponse fromEntityToGoogle(UserTokenEntity Entity){
        com.google.api.client.auth.oauth2.TokenResponse out = new com.google.api.client.auth.oauth2.TokenResponse();
        out.setAccessToken(Entity.getAccessToken());
        out.setTokenType(Entity.getTokenType());
        out.setScope(Entity.getScope());
        out.setExpiresInSeconds(new Long(Entity.getExpiresInSeconds()));
        return out;
    }

    public static com.google.api.client.auth.oauth2.TokenResponse fromDtoToGoogle(TokenResponse tokenResponse){
        com.google.api.client.auth.oauth2.TokenResponse out = new com.google.api.client.auth.oauth2.TokenResponse();
        out.setAccessToken(tokenResponse.getAccessToken());
        out.setRefreshToken(tokenResponse.getRefreshToken());
        out.setTokenType(tokenResponse.getTokenType());
        out.setScope(tokenResponse.getScope());
        out.setExpiresInSeconds(tokenResponse.getExpiresInSeconds());
        return out;
    }

    public static TokenResponse fromGoogleToDto(com.google.api.client.auth.oauth2.TokenResponse tokenResponse){
        TokenResponse out = new TokenResponse();
        out.setAccessToken(tokenResponse.getAccessToken());
        out.setRefreshToken(tokenResponse.getRefreshToken());
        out.setTokenType(tokenResponse.getTokenType());
        out.setScope(tokenResponse.getScope());
        out.setExpiresInSeconds(tokenResponse.getExpiresInSeconds());
        return out;
    }

    public static UserTokenEntity fromGoogleToEntity(com.google.api.client.auth.oauth2.TokenResponse tokenResponse , UtenteEntity utenteEntity , Integer id){
        UserTokenEntity out = new UserTokenEntity();
        if(id != null){
            out.setId(id);
        }
        out.setAccessToken(tokenResponse.getAccessToken());
        out.setTokenType(tokenResponse.getTokenType());
        out.setScope(tokenResponse.getScope());
        out.setExpiresInSeconds(tokenResponse.getExpiresInSeconds().intValue());
        out.setUtenteIdutente(utenteEntity);
        out.setDateCreation(Timestamp.from(Instant.now()));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, tokenResponse.getExpiresInSeconds().intValue());
        Timestamp later = new Timestamp(cal.getTime().getTime());
        out.setDateExiration(later);
        out.setProvider("GOOGLE");
        return out;
    }

    public static UserTokenEntity updateEntity(UserTokenEntity old , com.google.api.client.auth.oauth2.TokenResponse refreshedToken){
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
