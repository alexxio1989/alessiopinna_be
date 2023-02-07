package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.*;
import com.example.alessiopinnabe.dto.request.RequestCarrelloDto;
import com.example.alessiopinnabe.dto.response.ResponseAcquistoDto;
import com.example.alessiopinnabe.entity.Acquisto;
import com.example.alessiopinnabe.mapper.CalendarMapper;
import com.example.alessiopinnabe.mapper.EmailMapper;
import com.example.alessiopinnabe.mapper.mapstruct.AcquistoMapper;
import com.example.alessiopinnabe.repositories.AcquistoRepository;
import com.example.alessiopinnabe.repositories.TokenRepository;
import com.example.alessiopinnabe.util.Constants;
import com.google.api.services.calendar.model.Event;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
    @Autowired
    private AcquistoMapper acquistoMapper;


    @Transactional
    public ResponseAcquistoDto saveCarrello(RequestCarrelloDto carrello, TokenDto tokenResponseDto) {
        ResponseAcquistoDto out = new ResponseAcquistoDto();


        try {
            if(CollectionUtils.isNotEmpty(carrello.getAcquistoProdotti())){
                for (AcquistoProdottoDto acquistoProdottoDto:carrello.getAcquistoProdotti()) {
                    saveAcquistoProdotto(acquistoProdottoDto);
                }
            }
            if(CollectionUtils.isNotEmpty(carrello.getAcquistoEventi())){
                for (AcquistoEventoDto acquistoEventoDto:carrello.getAcquistoEventi()) {
                    saveAcquistoEvento(acquistoEventoDto,tokenResponseDto);
                }
            }
            //mailService.send(emailMapper.emailAddProdotto(acquistoDto));
            //mailService.send(emailMapper.emailAddPrenotazioneToMe(acquistoDto));

        } catch (DataAccessException | IOException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        //return getAll(carrello.getUtente().getId());
        return out;
    }

    private void saveAcquistoEvento(AcquistoEventoDto acquistoEventoDto , TokenDto tokenResponseDto) throws DataAccessException , IOException{
        String eventCalendarID = null;
        UtenteDto utente = acquistoEventoDto.getUtente();
        try {
            if(Constants.GOOGLE.equalsIgnoreCase(utente.getProvider())){
                Event eventCalendar = googleService.createEventCalendar(tokenResponseDto, CalendarMapper.getEventFromDto(acquistoEventoDto));
                if(eventCalendar != null){
                    eventCalendarID = eventCalendar.getId();
                }
            }
            Acquisto acquistoEntity = acquistoMapper.getAcquistoEventoEntity(acquistoEventoDto,eventCalendarID);
            acquistoRepository.save(acquistoEntity);
        } catch (DataAccessException ex){
            if(StringUtils.isNotEmpty(eventCalendarID)){
                googleService.deleteEventCalendar(tokenResponseDto,eventCalendarID);
            }
            throw ex;
        }
    }

    private void saveAcquistoProdotto(AcquistoProdottoDto acquistoProdottoDto) throws DataAccessException , IOException{
        Acquisto acquistoEntity = null;
        try {
            acquistoEntity = acquistoMapper.getAcquistoProdottoEntity(acquistoProdottoDto);
            acquistoRepository.save(acquistoEntity);
        } catch (DataAccessException ex){
            throw ex;
        }
    }



    public ResponseAcquistoDto delete(AcquistoDto acquistoDto, TokenDto tokenResponseDto) {
        ResponseAcquistoDto out = new ResponseAcquistoDto();

        try {
            Acquisto acquistoEntity;
            if(acquistoDto instanceof AcquistoEventoDto){
                AcquistoEventoDto acquistoEventoDto = (AcquistoEventoDto) acquistoDto;
                googleService.deleteEventCalendar(tokenResponseDto,acquistoEventoDto.getIdEventCalendar());
                acquistoEntity = acquistoMapper.getAcquistoEventoEntity((AcquistoEventoDto)acquistoDto);
            } else {
                acquistoEntity = acquistoMapper.getAcquistoProdottoEntity((AcquistoProdottoDto) acquistoDto);
            }
            acquistoRepository.delete(acquistoEntity);
            //mailService.send(emailMapper.emailRemovePrenotazione(acquistoDto));
            //mailService.send(emailMapper.emailRemovePrenotazioneToMe(acquistoDto));
        } catch (DataAccessException | IOException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        return getAll(acquistoDto.getUtente().getId());
    }

    public ResponseAcquistoDto getAll(String idUtente){
        ResponseAcquistoDto out = new ResponseAcquistoDto();
        try {
            List<Acquisto> all = acquistoRepository.findAll();
            out = acquistoMapper.getResponse(out,all);
        } catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }

        out.setSuccess(true);
        return out;
    }

}
