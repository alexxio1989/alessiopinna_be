package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.AcquistoDto;
import com.example.alessiopinnabe.dto.ResponseAcquistoDto;
import com.example.alessiopinnabe.dto.TokenDto;
import com.example.alessiopinnabe.mapper.CalendarMapper;
import com.example.alessiopinnabe.mapper.EmailMapper;
import com.example.alessiopinnabe.mapper.AcquistoMapper;
import com.example.alessiopinnabe.mapper.TokenMapper;
import com.example.alessiopinnabe.repositories.AcquistoRepository;
import com.example.alessiopinnabe.repositories.TokenRepository;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ServiceAcquisto {

    @Autowired
    private AcquistoRepository acquistoRepository;

    @Autowired
    private ServiceEmail mailService;

    @Autowired
    private EmailMapper emailMapper;

    @Autowired
    private ServiceGoogle googleService;

    @Autowired
    private TokenRepository userTokenRepository;

    public ResponseAcquistoDto save(AcquistoDto acquistoDto, TokenDto tokenResponseDto) {
        ResponseAcquistoDto out = new ResponseAcquistoDto();

        try {
            Event eventCalendar = null;
            if(acquistoDto.isEvento()){
                eventCalendar = googleService.createEventCalendar(tokenResponseDto, CalendarMapper.getEventFromDto(acquistoDto));
            }
            AcquistoEntity acquistoEntity = AcquistoMapper.getEntity(acquistoDto,eventCalendar);
            if(eventCalendar != null && eventCalendar.getId() != null){
                acquistoRepository.save(acquistoEntity);
                mailService.send(emailMapper.emailAddProdotto(acquistoDto));
                mailService.send(emailMapper.emailAddPrenotazioneToMe(acquistoDto));
            } else {
                out.setSuccess(false);
                out.setError("Errore durante la creazione dell'evento google");
            }
        } catch (DataAccessException | IOException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        if(acquistoDto.isFromDetail()){
            return getAllByUtenteAndProdotto(acquistoDto.getUtente().getId(),acquistoDto.getProdotto().getId(), tokenResponseDto);
        } else {
            return getAllByUtente(acquistoDto.getUtente().getId(), tokenResponseDto);
        }
    }



    public ResponseAcquistoDto delete(AcquistoDto acquistoDto, TokenDto tokenResponseDto) {
        ResponseAcquistoDto out = new ResponseAcquistoDto();

        try {
            googleService.deleteEventCalendar(tokenResponseDto,acquistoDto.getEvento().getIdEvent());
            acquistoRepository.delete(AcquistoMapper.getEntity(acquistoDto,null));
            mailService.send(emailMapper.emailRemovePrenotazione(acquistoDto));
            mailService.send(emailMapper.emailRemovePrenotazioneToMe(acquistoDto));
        } catch (DataAccessException | IOException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        if(acquistoDto.isFromDetail()){
            return getAllByUtenteAndProdotto(acquistoDto.getUtente().getId(),acquistoDto.getProdotto().getId(), tokenResponseDto);
        } else {
            return getAllByUtente(acquistoDto.getUtente().getId(), tokenResponseDto);
        }
    }

    public ResponseAcquistoDto getAll(){
        ResponseAcquistoDto out = new ResponseAcquistoDto();
        try {
            List<AcquistoEntity> all = acquistoRepository.findAll();
            out.setAcquisti(AcquistoMapper.getListDTO(all));

        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }

        out.setSuccess(true);
        return out;
    }

    public ResponseAcquistoDto getAllByUtente(Integer idUtente, TokenDto tokenResponseDto){
        ResponseAcquistoDto out = new ResponseAcquistoDto();
        try {
            if(tokenResponseDto != null){
                Credential credential = googleService.getCredential(TokenMapper.fromDtoToGoogle(tokenResponseDto));
                Calendar calendar = googleService.getCalendar(credential);
                List<Event> events = googleService.getEvents(calendar);

            }
            Date date = new Date();
            Timestamp ts=new Timestamp(date.getTime());
            List<AcquistoEntity> all = acquistoRepository.getAcquistiByUtente(idUtente,ts);
            out.setAcquistiUtente(AcquistoMapper.getListDTO(all));

        } catch (DataAccessException | IOException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        out.setSuccess(true);
        return out;
    }

    public ResponseAcquistoDto getAllByUtenteAndProdotto(Integer idUtente , Integer idProdotto , TokenDto tokenResponseDto){
        ResponseAcquistoDto out = new ResponseAcquistoDto();
        try {
            List<Event> events = null;
            if(tokenResponseDto != null){
                Credential credential = googleService.getCredential(TokenMapper.fromDtoToGoogle(tokenResponseDto));
                Calendar calendar = googleService.getCalendar(credential);
                events = googleService.getEvents(calendar);

            }
            Date date = new Date();
            Timestamp ts=new Timestamp(date.getTime());
            List<AcquistoEntity> all = acquistoRepository.getAcquistiByUtenteAndProdotto(idUtente,idProdotto,ts);
            List<AcquistoEntity> allUtente = acquistoRepository.getAcquistiByUtente(idUtente,ts);
            out.setAcquistiUtente(AcquistoMapper.getListDTO(allUtente));
            out.setAcquisti(AcquistoMapper.getListDTO(all));

        } catch (DataAccessException | IOException  ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        out.setSuccess(true);
        return out;
    }
}
