package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.UtenteEntity;

public class UtenteMapper{

    public static UtenteDto getDTO(UtenteEntity utente) {
        UtenteDto out = new UtenteDto();
        out.setId(utente.getId());
        out.setEmail(utente.getEmail());
        out.setSkypeID(utente.getSkypeID());
        out.setName(utente.getUsername());
        out.setTipo(DominioMapper.getTipoUtenteDTO(utente.getTplUtenteIdtplUtente()));
        out.setProvider(utente.getProvider());
        return out;
    }

    public static UtenteEntity getEntity(UtenteDto utente , String password) {
        UtenteEntity out = new UtenteEntity();
        out.setId(utente.getId());
        out.setEmail(utente.getEmail());
        out.setSkypeID(utente.getSkypeID());
        out.setUsername(utente.getName());
        out.setPassword(password);
        if(utente.getTipo() != null){
            out.setTplUtenteIdtplUtente(DominioMapper.getTipoUtenteEntity(utente.getTipo()));
        }
        out.setProvider(utente.getProvider());
        return out;
    }
}
