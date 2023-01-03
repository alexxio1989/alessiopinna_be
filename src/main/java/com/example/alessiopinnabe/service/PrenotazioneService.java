package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.PrenotazioneDto;
import com.example.alessiopinnabe.dto.ResponseCorsoDto;
import com.example.alessiopinnabe.dto.ResponsePrenotazioneDto;
import com.example.alessiopinnabe.entity.PrenotazioneEntity;
import com.example.alessiopinnabe.entity.PrenotazioneIdEntity;
import com.example.alessiopinnabe.entity.UtenteEntity;
import com.example.alessiopinnabe.mapper.PrenotazioneMapper;
import com.example.alessiopinnabe.mapper.UtenteMapper;
import com.example.alessiopinnabe.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public ResponsePrenotazioneDto save(PrenotazioneDto prenotazione) {
        ResponsePrenotazioneDto out = new ResponsePrenotazioneDto();

        try {
            prenotazioneRepository.save(PrenotazioneMapper.getEntity(prenotazione));
        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        if(prenotazione.isFromDetail()){
            return getAllByUtenteAndCorso(prenotazione.getUtente().getId(),prenotazione.getCorso().getId());
        } else {
            return getAllByUtente(prenotazione.getUtente().getId());
        }
    }

    public ResponsePrenotazioneDto delete(PrenotazioneDto prenotazione) {
        ResponsePrenotazioneDto out = new ResponsePrenotazioneDto();

        try {
            prenotazioneRepository.delete(PrenotazioneMapper.getEntity(prenotazione));
        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        if(prenotazione.isFromDetail()){
            return getAllByUtenteAndCorso(prenotazione.getUtente().getId(),prenotazione.getCorso().getId());
        } else {
            return getAllByUtente(prenotazione.getUtente().getId());
        }
    }

    public ResponsePrenotazioneDto getAll(){
        ResponsePrenotazioneDto out = new ResponsePrenotazioneDto();
        try {
            List<PrenotazioneEntity> all = prenotazioneRepository.findAll();
            out.setPrenotazioni(PrenotazioneMapper.getListDTO(all));

        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }

        out.setSuccess(true);
        return out;
    }

    public ResponsePrenotazioneDto getAllByUtente(Integer idUtente){
        ResponsePrenotazioneDto out = new ResponsePrenotazioneDto();
        try {
            Date date = new Date();
            Timestamp ts=new Timestamp(date.getTime());
            List<PrenotazioneEntity> all = prenotazioneRepository.getPrenotazioniByUtente(idUtente,ts);
            out.setPrenotazioni(PrenotazioneMapper.getListDTO(all));

        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        out.setSuccess(true);
        return out;
    }

    public ResponsePrenotazioneDto getAllByUtenteAndCorso(Integer idUtente , Integer idCorso){
        ResponsePrenotazioneDto out = new ResponsePrenotazioneDto();
        try {
            Date date = new Date();
            Timestamp ts=new Timestamp(date.getTime());
            List<PrenotazioneEntity> all = prenotazioneRepository.getPrenotazioniByUtenteAndCorso(idUtente,idCorso,ts);
            out.setPrenotazioni(PrenotazioneMapper.getListDTO(all));

        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }        out.setSuccess(true);
        return out;
    }
}
