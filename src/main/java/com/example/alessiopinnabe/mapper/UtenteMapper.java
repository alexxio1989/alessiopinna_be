package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.UtenteEntity;

public class UtenteMapper{

    public UtenteDto getDTO(UtenteEntity utente) {
        UtenteDto out = new UtenteDto();
        out.setId(utente.getId());
        out.setEmail(utente.getEmail());
        out.setSkypeID(utente.getSkypeID());
        out.setUsername(utente.getUsername());
        out.setTipo(DominioMapper.getTipoUtenteDTO(utente.getTplUtenteIdtplUtente()));
        return out;
    }

    public UtenteEntity getEntity(UtenteDto utente , String password) {
        UtenteEntity out = new UtenteEntity();
        out.setId(utente.getId());
        out.setEmail(utente.getEmail());
        out.setSkypeID(utente.getSkypeID());
        out.setUsername(utente.getUsername());
        out.setPassword(password);
        out.setTplUtenteIdtplUtente(DominioMapper.getTipoUtenteEntity(utente.getTipo()));
        return out;
    }
}
