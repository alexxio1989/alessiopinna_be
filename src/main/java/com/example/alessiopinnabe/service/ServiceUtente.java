package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.RequestLoginDto;
import com.example.alessiopinnabe.dto.ResponseUtenteDto;
import com.example.alessiopinnabe.entity.TplUtenteEntity;
import com.example.alessiopinnabe.entity.UtenteEntity;
import com.example.alessiopinnabe.mapper.UtenteMapper;
import com.example.alessiopinnabe.repositories.TplUtenteEntityRepository;
import com.example.alessiopinnabe.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ServiceUtente {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private TplUtenteEntityRepository tplUtenteEntityRepository;

    public ResponseUtenteDto signin(RequestLoginDto req){
        ResponseUtenteDto out = new ResponseUtenteDto();
        try {
            long emailUsed = utenteRepository.countEmail(req.getUtente().getEmail());
            if(emailUsed == 0){
                UtenteEntity entity = UtenteMapper.getEntity(req.getUtente(), req.getPassword());
                TplUtenteEntity userTpl = tplUtenteEntityRepository.getUSER();
                entity.setTplUtenteIdtplUtente(userTpl);
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
            UtenteEntity utenteSaved = utenteRepository.login(req.getUtente().getEmail(), req.getPassword());
            out.setUtente(UtenteMapper.getDTO(utenteSaved));
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
            long emailUsed = utenteRepository.countEmail(req.getUtente().getEmail());
            if(emailUsed == 0){
                UtenteEntity entity = UtenteMapper.getEntity(req.getUtente(), req.getPassword());
                TplUtenteEntity userTpl = tplUtenteEntityRepository.getUSER();
                entity.setTplUtenteIdtplUtente(userTpl);
                UtenteEntity utenteSaved = utenteRepository.save(entity);
            }
            UtenteEntity utenteSaved = utenteRepository.login(req.getUtente().getEmail(), req.getPassword());
            out.setUtente(UtenteMapper.getDTO(utenteSaved));
        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        out.setSuccess(true);
        return out;
    }

}
