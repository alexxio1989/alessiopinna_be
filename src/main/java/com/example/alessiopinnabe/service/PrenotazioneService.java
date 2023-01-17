package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.PrenotazioneDto;
import com.example.alessiopinnabe.dto.ResponsePrenotazioneDto;
import com.example.alessiopinnabe.entity.PrenotazioneEntity;
import com.example.alessiopinnabe.entity.UserTokenEntity;
import com.example.alessiopinnabe.mapper.CalendarMapper;
import com.example.alessiopinnabe.mapper.EmailMapper;
import com.example.alessiopinnabe.mapper.PrenotazioneMapper;
import com.example.alessiopinnabe.mapper.TokenMapper;
import com.example.alessiopinnabe.repositories.PrenotazioneRepository;
import com.example.alessiopinnabe.repositories.UserTokenRepository;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarRequest;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private EmailMapper emailMapper;

    @Autowired
    private GoogleService googleService;

    @Autowired
    private UserTokenRepository userTokenRepository;

    public ResponsePrenotazioneDto save(PrenotazioneDto prenotazione,com.example.alessiopinnabe.dto.TokenResponse tokenResponse) {
        ResponsePrenotazioneDto out = new ResponsePrenotazioneDto();

        try {
            Credential credential = googleService.getCredential(TokenMapper.fromDtoToGoogle(tokenResponse));
            Calendar calendar = googleService.getCalendar(credential);
            Event insert = googleService.addEvent(calendar, CalendarMapper.getEventFromDto(prenotazione));
            PrenotazioneEntity prenotazioneEntity = PrenotazioneMapper.getEntity(prenotazione);
            prenotazioneEntity.setIdEvent(insert.getId());
            if(insert != null && insert.getId() != null){
                prenotazioneRepository.save(prenotazioneEntity);
                mailService.send(emailMapper.emailAddPrenotazione(prenotazione));
                mailService.send(emailMapper.emailAddPrenotazioneToMe(prenotazione));
            } else {
                out.setSuccess(false);
                out.setError("Errore durante la creazione dell'evento google");
            }
        } catch (DataAccessException | IOException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        if(prenotazione.isFromDetail()){
            return getAllByUtenteAndCorso(prenotazione.getUtente().getId(),prenotazione.getCorso().getId(),tokenResponse);
        } else {
            return getAllByUtente(prenotazione.getUtente().getId(),tokenResponse);
        }
    }

    public ResponsePrenotazioneDto delete(PrenotazioneDto prenotazione, com.example.alessiopinnabe.dto.TokenResponse tokenResponse) {
        ResponsePrenotazioneDto out = new ResponsePrenotazioneDto();

        try {
            Credential credential = googleService.getCredential(TokenMapper.fromDtoToGoogle(tokenResponse));
            Calendar calendar = googleService.getCalendar(credential);
            googleService.removeEvent(calendar , prenotazione.getIdEvent());
            prenotazioneRepository.delete(PrenotazioneMapper.getEntity(prenotazione));
            mailService.send(emailMapper.emailRemovePrenotazione(prenotazione));
            mailService.send(emailMapper.emailRemovePrenotazioneToMe(prenotazione));
        } catch (DataAccessException | IOException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        if(prenotazione.isFromDetail()){
            return getAllByUtenteAndCorso(prenotazione.getUtente().getId(),prenotazione.getCorso().getId(),tokenResponse);
        } else {
            return getAllByUtente(prenotazione.getUtente().getId(),tokenResponse);
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

    public ResponsePrenotazioneDto getAllByUtente(Integer idUtente, com.example.alessiopinnabe.dto.TokenResponse tokenResponse){
        ResponsePrenotazioneDto out = new ResponsePrenotazioneDto();
        try {
            if(tokenResponse != null){
                Credential credential = googleService.getCredential(TokenMapper.fromDtoToGoogle(tokenResponse));
                Calendar calendar = googleService.getCalendar(credential);
                List<Event> events = googleService.getEvents(calendar);

            }
            Date date = new Date();
            Timestamp ts=new Timestamp(date.getTime());
            List<PrenotazioneEntity> all = prenotazioneRepository.getPrenotazioniByUtente(idUtente,ts);
            out.setPrenotazioniUtente(PrenotazioneMapper.getListDTO(all));

        } catch (DataAccessException | IOException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        out.setSuccess(true);
        return out;
    }

    public ResponsePrenotazioneDto getAllByUtenteAndCorso(Integer idUtente , Integer idCorso , com.example.alessiopinnabe.dto.TokenResponse tokenResponse){
        ResponsePrenotazioneDto out = new ResponsePrenotazioneDto();
        try {
            List<Event> events = null;
            if(tokenResponse != null){
                Credential credential = googleService.getCredential(TokenMapper.fromDtoToGoogle(tokenResponse));
                Calendar calendar = googleService.getCalendar(credential);
                events = googleService.getEvents(calendar);

            }
            Date date = new Date();
            Timestamp ts=new Timestamp(date.getTime());
            List<PrenotazioneEntity> all = prenotazioneRepository.getPrenotazioniByUtenteAndCorso(idUtente,idCorso,ts);
            List<PrenotazioneEntity> allUtente = prenotazioneRepository.getPrenotazioniByUtente(idUtente,ts);
            out.setPrenotazioniUtente(PrenotazioneMapper.getListDTO(allUtente));
            out.setPrenotazioni(PrenotazioneMapper.getListDTO(all));

        } catch (DataAccessException | IOException  ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        out.setSuccess(true);
        return out;
    }
}
