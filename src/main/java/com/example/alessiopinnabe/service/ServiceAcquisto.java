package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.components.EmailSender;
import com.example.alessiopinnabe.components.GoogleManager;
import com.example.alessiopinnabe.dto.*;
import com.example.alessiopinnabe.dto.request.RequestAcquistoDto;
import com.example.alessiopinnabe.dto.response.ResponseAcquistoDto;
import com.example.alessiopinnabe.dto.response.ResponseServiziDto;
import com.example.alessiopinnabe.entity.Acquisto;
import com.example.alessiopinnabe.entity.AcquistoEvento;
import com.example.alessiopinnabe.exceptions.CoreException;
import com.example.alessiopinnabe.mapper.EmailMapper;
import com.example.alessiopinnabe.mapper.mapstruct.AcquistoMapper;
import com.example.alessiopinnabe.repositories.AcquistoRepository;
import com.example.alessiopinnabe.repositories.TokenRepository;
import com.example.alessiopinnabe.service.core.CrudService;
import com.example.alessiopinnabe.util.Constants;
import com.google.api.services.calendar.model.Event;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceAcquisto implements CrudService<RequestAcquistoDto, ResponseAcquistoDto> {

    @Autowired
    private AcquistoRepository acquistoRepository;
    @Autowired
    private EmailSender mailService;
    @Autowired
    private EmailMapper emailMapper;
    @Autowired
    private GoogleManager googleService;
    @Autowired
    private TokenRepository userTokenRepository;
    @Autowired
    private AcquistoMapper acquistoMapper;

    @Transactional
    public ResponseEntity<ResponseAcquistoDto>  save(RequestAcquistoDto request, TokenDto tokenResponseDto) {
        ResponseAcquistoDto out = new ResponseAcquistoDto();
        if(CollectionUtils.isNotEmpty(request.getAcquisti())){
            for (AcquistoDto acquistoDto:request.getAcquisti()) {
                saveAcquisto(acquistoDto,tokenResponseDto);
            }
        }
        //mailService.send(emailMapper.emailAddProdotto(acquistoDto));
        //mailService.send(emailMapper.emailAddPrenotazioneToMe(acquistoDto));
        return getAll(request.getUtente().getId());
    }

    @Override
    public ResponseEntity<ResponseAcquistoDto> delete(String id, TokenDto tokenResponseDto) {
        ResponseAcquistoDto out = new ResponseAcquistoDto();
        Acquisto entity = null;
        try {

            Optional<Acquisto> byId = acquistoRepository.findById(id);
            if(byId.isPresent()){
                entity = byId.get();
                acquistoRepository.delete(entity);
                if(entity instanceof AcquistoEvento){
                    AcquistoEvento evento = (AcquistoEvento) entity;
                    if(tokenResponseDto != null && StringUtils.isNotEmpty(evento.getIdEventCalendar())){
                        googleService.deleteEventCalendar(tokenResponseDto,evento.getIdEventCalendar());
                    }
                }
            } else {
                throw new CoreException("Acquisto con id " + id + " non trovato", HttpStatus.BAD_REQUEST , null);
            }

            //mailService.send(emailMapper.emailRemovePrenotazione(acquistoDto));
            //mailService.send(emailMapper.emailRemovePrenotazioneToMe(acquistoDto));
        } catch (DataAccessException | IOException ex){
            throw new CoreException("Errore durante l'eliminazione dell'acquisto'", HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
        }
        return getAll(entity.getUtente().getId());
    }

    @Override
    public ResponseEntity<ResponseAcquistoDto> getAll() {
        throw new CoreException("Metodo non implementato",HttpStatus.METHOD_NOT_ALLOWED , null);
    }

    @Override
    public ResponseEntity<ResponseAcquistoDto> getAll(String idUtente){
        ResponseAcquistoDto out = new ResponseAcquistoDto();
        try {
            List<Acquisto> all = acquistoRepository.findAll();
            out = acquistoMapper.getResponse(out,all);
        } catch (DataAccessException ex){
            throw new CoreException("Errore durante il recupero degli acquisti'", HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
        }
        ResponseEntity<ResponseAcquistoDto> response = new ResponseEntity<>(out, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<ResponseAcquistoDto> get(RequestAcquistoDto request, TokenDto token) {
        throw new CoreException("Metodo non implementato",HttpStatus.METHOD_NOT_ALLOWED , null);
    }

    private void saveAcquisto(AcquistoDto acquistoDto , TokenDto tokenResponseDto){
        String eventCalendarID = null;
        UtenteDto utente = acquistoDto.getUtente();
        try {
            Acquisto acquistoEntity = acquistoMapper.getEntity(acquistoDto,tokenResponseDto,false);
            acquistoRepository.save(acquistoEntity);
        } catch (DataAccessException | IOException ex){
            throw new CoreException("Errore durante il salvataggio dell'acquisto ", HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
        }
    }

}
