package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.request.RequestLoginDto;
import com.example.alessiopinnabe.dto.response.ResponseUtenteDto;
import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.Token;

import com.example.alessiopinnabe.entity.TplUtente;

import com.example.alessiopinnabe.entity.Utente;

import com.example.alessiopinnabe.mapper.TokenMapper;
import com.example.alessiopinnabe.mapper.mapstruct.UtenteMapper;
import com.example.alessiopinnabe.repositories.TplUtenteRepository;
import com.example.alessiopinnabe.repositories.TokenRepository;
import com.example.alessiopinnabe.repositories.UtenteRepository;
import com.example.alessiopinnabe.util.Constants;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.oauth2.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Utente loginFromGoogle(Userinfo userInfo, TokenResponse token){
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
        Token tokenRetrieved = tokenRepository.getByProvidersAndUser(utenteSaved.getId(), Constants.GOOGLE);
        Token newToken = TokenMapper.fromGoogleToEntity(token, utenteSaved, tokenRetrieved != null ? tokenRetrieved.getId() : null);
        tokenRepository.save(newToken);
        return utenteSaved;
    }

}
