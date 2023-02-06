package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.*;
import com.example.alessiopinnabe.entity._Acquisto;
import com.example.alessiopinnabe.mapper.CalendarMapper;
import com.example.alessiopinnabe.mapper.EmailMapper;
import com.example.alessiopinnabe.mapper.mapstruct.AcquistoMapper;
import com.example.alessiopinnabe.repositories.AcquistoRepository;
import com.example.alessiopinnabe.repositories.TokenRepository;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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


    public ResponseAcquistoDto save(AcquistoDto acquistoDto, TokenDto tokenResponseDto) {
        ResponseAcquistoDto out = new ResponseAcquistoDto();

        try {
            Event eventCalendar = null;
            _Acquisto acquistoEntity;
            if(acquistoDto instanceof AcquistoEventoDto){
                eventCalendar = googleService.createEventCalendar(tokenResponseDto, CalendarMapper.getEventFromDto(acquistoDto));
                acquistoEntity = acquistoMapper.getAcquistoEventoEntity((AcquistoEventoDto)acquistoDto,eventCalendar.getId());
            } else {
                acquistoEntity = acquistoMapper.getAcquistoProdottoEntity((AcquistoProdottoDto) acquistoDto);
            }
            acquistoRepository.save(acquistoEntity);
            mailService.send(emailMapper.emailAddProdotto(acquistoDto));
            mailService.send(emailMapper.emailAddPrenotazioneToMe(acquistoDto));

        } catch (DataAccessException | IOException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }
        return getAll(acquistoDto.getUtente().getId());
    }



    public ResponseAcquistoDto delete(AcquistoDto acquistoDto, TokenDto tokenResponseDto) {
        ResponseAcquistoDto out = new ResponseAcquistoDto();

        try {
            _Acquisto acquistoEntity;
            if(acquistoDto instanceof AcquistoEventoDto){
                AcquistoEventoDto acquistoEventoDto = (AcquistoEventoDto) acquistoDto;
                googleService.deleteEventCalendar(tokenResponseDto,acquistoEventoDto.getIdEventCalendar());
                acquistoEntity = acquistoMapper.getAcquistoEventoEntity((AcquistoEventoDto)acquistoDto);
            } else {
                acquistoEntity = acquistoMapper.getAcquistoProdottoEntity((AcquistoProdottoDto) acquistoDto);
            }
            acquistoRepository.delete(acquistoEntity);
            mailService.send(emailMapper.emailRemovePrenotazione(acquistoDto));
            mailService.send(emailMapper.emailRemovePrenotazioneToMe(acquistoDto));
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
            List<_Acquisto> all = acquistoRepository.findAll();
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
