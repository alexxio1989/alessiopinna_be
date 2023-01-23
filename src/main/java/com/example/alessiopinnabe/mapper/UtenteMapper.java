package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.TokenResponseDto;
import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.TokenEntity;
import com.example.alessiopinnabe.entity.TplUtenteEntity;
import com.example.alessiopinnabe.entity.UtenteEntity;
import com.google.api.services.oauth2.model.Userinfo;

import java.util.ArrayList;
import java.util.List;

public class UtenteMapper{

    public static UtenteDto getDTO(UtenteEntity utente) {
        UtenteDto out = new UtenteDto();
        out.setId(utente.getId());
        out.setEmail(utente.getEmail());
        out.setName(utente.getUsername());
        out.setTipo(DominioMapper.getTipoUtenteDTO(utente.getTplUtente()));
        out.setProvider(utente.getProvider());
        List<TokenResponseDto> tokenResponsDtos = new ArrayList<>();

        if(utente.getTokens() != null && utente.getTokens().size() > 0){
            for (TokenEntity tokenEntity:
                    utente.getTokens()) {
                tokenResponsDtos.add(TokenMapper.fromEntityToDto(tokenEntity));
            }
        }
        out.setTokens(tokenResponsDtos);
        out.setAcquisti(AcquistoMapper.getListDTO(utente.getAcquisti()));
        return out;
    }

    public static UtenteEntity getEntity(UtenteDto utente , String password) {
        UtenteEntity out = new UtenteEntity();
        out.setId(utente.getId());
        out.setEmail(utente.getEmail());
        out.setUsername(utente.getName());
        out.setPassword(password);
        if(utente.getTipo() != null){
            out.setTplUtente(DominioMapper.getTipoUtenteEntity(utente.getTipo()));
        }
        out.setProvider(utente.getProvider());
        return out;
    }


    public static UtenteEntity getEntityFromGoogle(Userinfo userInfo , TplUtenteEntity userTpl) {
        UtenteEntity out = new UtenteEntity();
        out.setEmail(userInfo.getEmail());
        out.setUsername(userInfo.getName());
        out.setPassword(userInfo.getId());
        if(userTpl != null){
            out.setTplUtente(userTpl);
        }
        out.setProvider("GOOGLE");
        return out;
    }
}
