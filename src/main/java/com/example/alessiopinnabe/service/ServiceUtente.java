package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.config.JwtProvider;
import com.example.alessiopinnabe.dto.UtenteDtoFull;
import com.example.alessiopinnabe.dto.request.RequestLoginDto;
import com.example.alessiopinnabe.dto.response.ResponseUtenteDto;
import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.Token;

import com.example.alessiopinnabe.entity.TplUtente;

import com.example.alessiopinnabe.entity.Utente;

import com.example.alessiopinnabe.mapper.mapstruct.TokenMapper;
import com.example.alessiopinnabe.mapper.mapstruct.UtenteMapper;
import com.example.alessiopinnabe.repositories.TplUtenteRepository;
import com.example.alessiopinnabe.repositories.TokenRepository;
import com.example.alessiopinnabe.repositories.UtenteRepository;
import com.example.alessiopinnabe.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.oauth2.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceUtente {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private TplUtenteRepository tplUtenteEntityRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UtenteMapper utenteMapper;
    @Autowired
    private TokenMapper tokenMapper;

    @Transactional
    public ResponseUtenteDto signin(RequestLoginDto req){
        ResponseUtenteDto out = new ResponseUtenteDto();
        try {
            long emailUsed = utenteRepository.countEmail(req.getUtente().getEmail());
            if(emailUsed == 0){
                Utente entity = utenteMapper.getEntity(req.getUtente(), req.getPassword());
                TplUtente userTpl = tplUtenteEntityRepository.getUSER();
                entity.setTipoUtente(userTpl);
                Utente utenteSaved = utenteRepository.save(entity);
                out.setUtente(utenteMapper.getDto(utenteSaved));
            } else {
                out.setSuccess(false);
                out.setError("EMAIL UTILIZZATA NON DISPONIBILE");
                return out;
            }

        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        out.setSuccess(true);
        return out;
    }


    @Transactional
    public ResponseUtenteDto login(RequestLoginDto req){
        ResponseUtenteDto out = new ResponseUtenteDto();
        try {
            Utente utenteSaved = utenteRepository.findByEmailAndPassword(req.getUtente().getEmail(), req.getPassword());
            if(utenteSaved != null){
                UtenteDto utenteDTO = utenteMapper.getDto(utenteSaved);
                out.setUtente(utenteDTO);
            } else {
                out.setSuccess(false);
                out.setError("UTENTE NON TROVATO");
                return out;
            }

        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        out.setSuccess(true);
        return out;
    }


    @Transactional
    public ResponseUtenteDto loginAdmin(RequestLoginDto req){
        ResponseUtenteDto out = new ResponseUtenteDto();
        try {
            Utente utenteSaved = utenteRepository.findByEmailAndPassword(req.getUtente().getEmail(), req.getPassword());
            if(utenteSaved != null){
                UtenteDto utenteDTO = utenteMapper.getDto(utenteSaved);
                Token tokenEntityDefault = tokenRepository.getByProvidersAndUser(utenteSaved.getId(), Constants.DEFAULT);
                String tokenDefaultGenerated = generateJWT(utenteDTO, utenteSaved.getPassword());
                Token newTokenEntityDefault = tokenMapper.fromDefaultToEntity(tokenDefaultGenerated, utenteSaved, tokenEntityDefault);
                tokenRepository.save(newTokenEntityDefault);
                out.setUtente(utenteDTO);
            } else {
                out.setSuccess(false);
                out.setError("UTENTE NON TROVATO");
                return out;
            }

        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        out.setSuccess(true);
        return out;
    }

    @Transactional
    public UtenteDtoFull loginFromGoogle(Userinfo userInfo, TokenResponse tokenGOOGLE){
        UtenteDtoFull out = new UtenteDtoFull();
        Utente utenteSaved = null;
        long emailUsed = utenteRepository.countEmail(userInfo.getEmail());
        if(emailUsed == 0){
            TplUtente userTpl = tplUtenteEntityRepository.getUSER();
            Utente entity = utenteMapper.getEntityFromGoogle(userInfo);
            entity.setTipoUtente(userTpl);
            utenteSaved = utenteRepository.save(entity);
        } else {
            utenteSaved = utenteRepository.findByEmailAndPassword(userInfo.getEmail(), userInfo.getId());
        }
        Token tokenEntityGoogle = tokenRepository.getByProvidersAndUser(utenteSaved.getId(), Constants.GOOGLE);
        Token newTokenEntityGoogle = tokenMapper.fromGoogleToEntity(tokenGOOGLE, utenteSaved, tokenEntityGoogle);
        tokenRepository.save(newTokenEntityGoogle);

        UtenteDto utenteDto = utenteMapper.getDto(utenteSaved);

        Token tokenEntityDefault = tokenRepository.getByProvidersAndUser(utenteSaved.getId(), Constants.DEFAULT);
        String tokenDefaultGenerated = generateJWT(utenteDto, utenteSaved.getPassword());
        Token newTokenEntityDefault = tokenMapper.fromDefaultToEntity(tokenDefaultGenerated, utenteSaved, tokenEntityDefault);
        tokenRepository.save(newTokenEntityDefault);

        out.setUtente(utenteDto);
        out.setPassword(utenteSaved.getPassword());
        return out;
    }

    private String generateJWT(UtenteDto utente , String password){
        ObjectNode userNode = new ObjectMapper().convertValue(utente, ObjectNode.class);
        userNode.remove("password");
        Map claimMap = new HashMap(0);
        claimMap.put("user", userNode);
        String jwt = JwtProvider.createJwt(utente.getEmail(), claimMap);
        return "Bearer_"+jwt;
    }

}
