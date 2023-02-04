package com.example.alessiopinnabe.mapper.mapstruct;

import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.Utente;
import com.google.api.services.oauth2.model.Userinfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UtenteMapper {

    @Mapping(target = "nAcquistiProdotti" , expression = "java(utente.getProdottiAcquistati().size())")
    @Mapping(target = "nAcquistiEventi" , expression = "java(utente.getEventiAcquistati().size())")
    UtenteDto getDto(Utente utente);

    @Mapping(target = "password" , source = "password")
    Utente getEntity(UtenteDto utenteDto, String password);

    @Mapping(target = "anagrafica" , source = "name")
    @Mapping(target = "password" , source = "id")
    @Mapping(target = "anagrafica" , source = "name")
    @Mapping(target = "photoUrl" , source = "picture")
    @Mapping(target = "provider" , constant = "GOOGLE")
    Utente getEntityFromGoogle(Userinfo userInfo);
}
