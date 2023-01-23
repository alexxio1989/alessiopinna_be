package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.RequestLoginDto;
import com.example.alessiopinnabe.dto.ResponseUtenteDto;
import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.TokenEntity;
import com.example.alessiopinnabe.entity.TplUtenteEntity;
import com.example.alessiopinnabe.entity.UtenteEntity;
import com.example.alessiopinnabe.mapper.TokenMapper;
import com.example.alessiopinnabe.mapper.UtenteMapper;
import com.example.alessiopinnabe.repositories.AcquistoRepository;
import com.example.alessiopinnabe.repositories.TplUtenteEntityRepository;
import com.example.alessiopinnabe.repositories.TokenRepository;
import com.example.alessiopinnabe.repositories.UtenteRepository;
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
    private TplUtenteEntityRepository tplUtenteEntityRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Transactional
    public ResponseUtenteDto signin(RequestLoginDto req){
        ResponseUtenteDto out = new ResponseUtenteDto();
        try {
            long emailUsed = utenteRepository.countEmail(req.getUtente().getEmail());
            if(emailUsed == 0){
                UtenteEntity entity = UtenteMapper.getEntity(req.getUtente(), req.getPassword());
                TplUtenteEntity userTpl = tplUtenteEntityRepository.getUSER();
                entity.setTplUtente(userTpl);
                UtenteEntity utenteSaved = utenteRepository.save(entity);
                out.setUtente(UtenteMapper.getDTO(utenteSaved));
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
            UtenteEntity utenteSaved = utenteRepository.login(req.getUtente().getEmail(), req.getPassword());
            if(utenteSaved != null){
                UtenteDto utenteDTO = UtenteMapper.getDTO(utenteSaved);
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
    public UtenteEntity fromGoogle(Userinfo userInfo, TokenResponse token){
        UtenteEntity utenteSaved = null;
        long emailUsed = utenteRepository.countEmail(userInfo.getEmail());
        if(emailUsed == 0){
            TplUtenteEntity userTpl = tplUtenteEntityRepository.getUSER();
            UtenteEntity entity = UtenteMapper.getEntityFromGoogle(userInfo, userTpl);
            entity.setTplUtente(userTpl);
            utenteSaved = utenteRepository.save(entity);
        } else {
            utenteSaved = utenteRepository.login(userInfo.getEmail(), userInfo.getId());
        }
        TokenEntity tokenRetrieved = tokenRepository.getByProvidersAndUser(utenteSaved.getId(), "GOOGLE");
        TokenEntity newToken = TokenMapper.fromGoogleToEntity(token, utenteSaved, tokenRetrieved != null ? tokenRetrieved.getId() : null);
        tokenRepository.save(newToken);
        return utenteSaved;
    }

}
