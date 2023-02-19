package com.example.alessiopinnabe.mapper.mapstruct;

import com.example.alessiopinnabe.components.GoogleManager;
import com.example.alessiopinnabe.dto.*;
import com.example.alessiopinnabe.dto.response.ResponseAcquistoDto;
import com.example.alessiopinnabe.entity.Acquisto;
import com.example.alessiopinnabe.entity.AcquistoEvento;
import com.example.alessiopinnabe.entity.AcquistoProdotto;
import com.example.alessiopinnabe.mapper.mapstruct.util.AcquistoMapperUtil;
import com.example.alessiopinnabe.mapper.mapstruct.util.ServizioMapperUtil;
import com.example.alessiopinnabe.util.Constants;
import com.google.api.services.calendar.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

@Mapper( imports = AcquistoMapperUtil.class )
public abstract class AcquistoMapper {

    @Autowired
    private GoogleManager googleService;
    @Autowired
    private CalendarMapper calendarMapper;

    public Acquisto getEntity(AcquistoDto dto, TokenDto tokenResponseDto , boolean delete) throws IOException {
        Acquisto acquistoEntity;
        String eventCalendarID = null;
        if(dto instanceof AcquistoEventoDto){
            AcquistoEventoDto dtoEvent = (AcquistoEventoDto) dto;
            if(tokenResponseDto != null){
                if(delete){
                    googleService.deleteEventCalendar(tokenResponseDto,dtoEvent.getIdEventCalendar());
                } else {
                    UtenteDto utente = dto.getUtente();
                    if(Constants.GOOGLE.equalsIgnoreCase(utente.getProvider())){
                        Event eventCalendar = googleService.createEventCalendar(tokenResponseDto, calendarMapper.getEventFromDto(dtoEvent));
                        if(eventCalendar != null){
                            eventCalendarID = eventCalendar.getId();
                        }
                    }
                }
            }
            acquistoEntity = getAcquistoEventoEntity(dtoEvent,eventCalendarID);
        } else {
            acquistoEntity = getAcquistoProdottoEntity((AcquistoProdottoDto) dto);
        }
        return acquistoEntity;
    }

    @Mapping(target = "idEventCalendar" , source = "idEventCalendar")
    @Mapping(target = "dataAcquisto" , expression = "java(new java.sql.Timestamp(new java.util.Date().getTime()))")
    abstract AcquistoEvento getAcquistoEventoEntity(AcquistoEventoDto dto, String idEventCalendar);
    @Mapping(target = "dataAcquisto" , expression = "java(new java.sql.Timestamp(new java.util.Date().getTime()))")
    abstract AcquistoProdotto getAcquistoProdottoEntity(AcquistoProdottoDto dto);


    @Mapping(target = "acquistoProdotti" , expression = "java(getAcquistoProdottoDtoList(AcquistoMapperUtil.getAcquistiProdotti(entityList)))")
    @Mapping(target = "acquistoEventi" , expression = "java(getAcquistoEventoDtoList(AcquistoMapperUtil.getAcquistiEventi(entityList)))")
    public abstract ResponseAcquistoDto getResponse(ResponseAcquistoDto input , List<Acquisto> entityList);
    abstract List<AcquistoProdottoDto> getAcquistoProdottoDtoList(List<AcquistoProdotto> entityList);
    abstract List<AcquistoEventoDto> getAcquistoEventoDtoList(List<AcquistoEvento> entityList);
}
