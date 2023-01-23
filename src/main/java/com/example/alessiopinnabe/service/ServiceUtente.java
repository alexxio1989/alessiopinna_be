package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.RequestLoginDto;
import com.example.alessiopinnabe.dto.ResponseUtenteDto;
import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.AcquistoEntity;
import com.example.alessiopinnabe.entity.TokenEntity;
import com.example.alessiopinnabe.entity.TplUtenteEntity;
import com.example.alessiopinnabe.entity.UtenteEntity;
import com.example.alessiopinnabe.mapper.AcquistoMapper;
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
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ServiceUtente {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private TplUtenteEntityRepository tplUtenteEntityRepository;

    @Autowired
    private AcquistoRepository prenotazioneRepository;

    @Autowired
    private TokenRepository tokenRepository;
    
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
                out.setError("EMAIL NON DISPONIBILE");
            }

        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        out.setSuccess(true);
        return out;
    }


    public ResponseUtenteDto login(RequestLoginDto req){
        ResponseUtenteDto out = new ResponseUtenteDto();
        try {
            UtenteDto utenteDTO = getUtenteDto(req);
            out.setUtente(utenteDTO);
        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        out.setSuccess(true);
        return out;
    }

    public ResponseUtenteDto socialSignin(@RequestBody RequestLoginDto req){
        ResponseUtenteDto out = new ResponseUtenteDto();
        try {
            UtenteDto utenteDTO = getUtenteSocial(req);
            out.setUtente(utenteDTO);
        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        out.setSuccess(true);

        return out;
    }

    public UtenteDto getUtenteSocial(RequestLoginDto req) {
        String email = req.getUtente().getEmail();
        long emailUsed = utenteRepository.countEmail(email);
        if(emailUsed == 0){
            UtenteEntity entity = UtenteMapper.getEntity(req.getUtente(), req.getPassword());
            TplUtenteEntity userTpl = tplUtenteEntityRepository.getUSER();
            entity.setTplUtente(userTpl);
            UtenteEntity utenteSaved = utenteRepository.save(entity);
        }
        UtenteDto utenteDTO = getUtenteDto(req);
        return utenteDTO;
    }

    @Transactional
    public UtenteEntity fromGoogle(Userinfo userInfo, TokenResponse token){
        UtenteEntity utenteSaved = null;
        long emailUsed = utenteRepository.countEmail(userInfo.getEmail());
        if(emailUsed == 0){
            TplUtenteEntity userTpl = tplUtenteEntityRepository.getUSER();
            UtenteEntity entity = UtenteMapper.getSocialEntity(userInfo, userTpl);
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

    private UtenteDto getUtenteDto(RequestLoginDto req) {
        UtenteEntity utenteSaved = utenteRepository.login(req.getUtente().getEmail(), req.getPassword());
        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        List<AcquistoEntity> acquistiByUtente = prenotazioneRepository.getAcquistiByUtente(utenteSaved.getId(),ts);
        UtenteDto utenteDTO = UtenteMapper.getDTO(utenteSaved);
        utenteDTO.setAcquisti(AcquistoMapper.getListDTO(acquistiByUtente));
        return utenteDTO;
    }

}
