package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.RequestLoginDto;
import com.example.alessiopinnabe.dto.ResponseUtenteDto;
import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.PrenotazioneEntity;
import com.example.alessiopinnabe.entity.TplUtenteEntity;
import com.example.alessiopinnabe.entity.UtenteEntity;
import com.example.alessiopinnabe.mapper.PrenotazioneMapper;
import com.example.alessiopinnabe.mapper.UtenteMapper;
import com.example.alessiopinnabe.repositories.PrenotazioneRepository;
import com.example.alessiopinnabe.repositories.TplUtenteEntityRepository;
import com.example.alessiopinnabe.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
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
    private PrenotazioneRepository prenotazioneRepository;
    
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
            long emailUsed = utenteRepository.countEmail(req.getUtente().getEmail());
            if(emailUsed == 0){
                UtenteEntity entity = UtenteMapper.getEntity(req.getUtente(), req.getPassword());
                TplUtenteEntity userTpl = tplUtenteEntityRepository.getUSER();
                entity.setTplUtenteIdtplUtente(userTpl);
                UtenteEntity utenteSaved = utenteRepository.save(entity);
            }
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

    private UtenteDto getUtenteDto(RequestLoginDto req) {
        UtenteEntity utenteSaved = utenteRepository.login(req.getUtente().getEmail(), req.getPassword());
        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        List<PrenotazioneEntity> prenotazioniByUtente = prenotazioneRepository.getPrenotazioniByUtente(utenteSaved.getId(),ts);
        UtenteDto utenteDTO = UtenteMapper.getDTO(utenteSaved);
        utenteDTO.setPrenotazioni(PrenotazioneMapper.getListDTO(prenotazioniByUtente));
        return utenteDTO;
    }

}
