package com.example.alessiopinnabe.mapper.mapstruct;

import com.example.alessiopinnabe.entity.Token;
import com.example.alessiopinnabe.entity.Utente;
import com.example.alessiopinnabe.mapper.mapstruct.util.ServizioMapperUtil;
import com.example.alessiopinnabe.mapper.mapstruct.util.TokenMapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( imports = TokenMapperUtil.class )
public interface TokenMapper {

    @Mapping(target = "id" , expression = "java(tokenEntityGoogle != null && tokenEntityGoogle.getId() != null ? tokenEntityGoogle.getId() : null)")
    @Mapping(target = "accessToken" , expression = "java(tokenResponse.getAccessToken())")
    @Mapping(target = "tokenType" , expression = "java(tokenResponse.getTokenType())")
    @Mapping(target = "expiresInSeconds" , expression = "java(tokenResponse.getExpiresInSeconds().intValue())")
    @Mapping(target = "scope" , expression = "java(tokenResponse.getScope())")
    @Mapping(target = "dateCreation" , expression = "java(java.sql.Timestamp.from(java.time.Instant.now()))")
    @Mapping(target = "dateExiration" , expression = "java(TokenMapperUtil.addSeconds(tokenResponse.getExpiresInSeconds().intValue()))")
    @Mapping(target = "provider" , constant = "GOOGLE")
    @Mapping(target = "utente" , source = "utenteEntity")
    Token fromGoogleToEntity(com.google.api.client.auth.oauth2.TokenResponse tokenResponse , Utente utenteEntity , Token tokenEntityGoogle);

    @Mapping(target = "id" , expression = "java(tokenEntityGoogle != null && tokenEntityGoogle.getId() != null ? tokenEntityGoogle.getId() : null)")
    @Mapping(target = "accessToken" , source = "token")
    @Mapping(target = "dateCreation" , expression = "java(java.sql.Timestamp.from(java.time.Instant.now()))")
    @Mapping(target = "dateExiration" , expression = "java(TokenMapperUtil.getExiredTime(token))")
    @Mapping(target = "provider" , constant = "DEFAULT")
    @Mapping(target = "utente" , source = "utenteEntity")
    Token fromDefaultToEntity(String token , Utente utenteEntity , Token tokenEntityGoogle);

}
