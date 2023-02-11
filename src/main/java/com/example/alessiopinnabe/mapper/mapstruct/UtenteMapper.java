package com.example.alessiopinnabe.mapper.mapstruct;

import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.Utente;
import com.example.alessiopinnabe.util.Constants;
import com.google.api.services.oauth2.model.Userinfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UtenteMapper {

    @Mapping(target = "totAcquistiProdotti" , expression = "java(utente.getProdottiAcquistati() != null ? utente.getProdottiAcquistati().size() : 0)")
    @Mapping(target = "totAcquistiEventi" , expression = "java(utente.getEventiAcquistati() != null ? utente.getEventiAcquistati().size() : 0)")
    UtenteDto getDto(Utente utente);
    UtenteDto getDtoLight(Utente utente);

    @Mapping(target = "password" , source = "password")
    @Mapping(target = "provider" , constant = Constants.DEFAULT)
    Utente getEntity(UtenteDto utenteDto, String password);

    @Mapping(target = "anagrafica" , source = "name")
    @Mapping(target = "password" , source = "id")
    @Mapping(target = "photoUrl" , source = "picture")
    @Mapping(target = "provider" , constant = Constants.GOOGLE)
    Utente getEntityFromGoogle(Userinfo userInfo);
}
