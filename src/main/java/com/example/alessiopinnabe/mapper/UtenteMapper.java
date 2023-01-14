package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.DominioDto;
import com.example.alessiopinnabe.dto.RequestLoginDto;
import com.example.alessiopinnabe.dto.ResponseUtenteDto;
import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.TplUtenteEntity;
import com.example.alessiopinnabe.entity.UserTokenEntity;
import com.example.alessiopinnabe.entity.UtenteEntity;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.oauth2.model.Userinfo;

import java.util.ArrayList;
import java.util.List;

public class UtenteMapper{

    public static RequestLoginDto getRequestUtenteFromGoogle(Userinfo userinfo , TokenResponse response) {
        RequestLoginDto req = new RequestLoginDto();
        UtenteDto out = new UtenteDto();

        out.setEmail(userinfo.getEmail());
        out.setName(userinfo.getName());
        out.setProvider("GOOGLE");
        req.setPassword(userinfo.getId());
        req.setUtente(out);
        return req;
    }

    public static UtenteDto getDTO(UtenteEntity utente) {
        UtenteDto out = new UtenteDto();
        out.setId(utente.getId());
        out.setEmail(utente.getEmail());
        out.setSkypeID(utente.getSkypeID());
        out.setName(utente.getUsername());
        out.setTipo(DominioMapper.getTipoUtenteDTO(utente.getTplUtenteIdtplUtente()));
        out.setProvider(utente.getProvider());
        List<com.example.alessiopinnabe.dto.TokenResponse> tokenResponses = new ArrayList<>();

        if(utente.getUserTokens() != null && utente.getUserTokens().size() > 0){
            for (UserTokenEntity tokenEntity:
                    utente.getUserTokens()) {
                tokenResponses.add(TokenMapper.fromEntityToDto(tokenEntity));
            }
        }
        out.setTokens(tokenResponses);

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


    public static UtenteEntity getSocialEntity(Userinfo userInfo , TplUtenteEntity userTpl) {
        UtenteEntity out = new UtenteEntity();
        out.setEmail(userInfo.getEmail());
        out.setUsername(userInfo.getName());
        out.setPassword(userInfo.getId());
        if(userTpl != null){
            out.setTplUtenteIdtplUtente(userTpl);
        }
        out.setProvider("GOOGLE");
        return out;
    }
}
