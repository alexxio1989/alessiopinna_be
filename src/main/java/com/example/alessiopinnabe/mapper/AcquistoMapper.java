package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.AcquistoDto;
import com.example.alessiopinnabe.dto.EventoDto;
import com.example.alessiopinnabe.entity.AcquistoEntity;
import com.example.alessiopinnabe.entity.EventoEntity;
import com.google.api.services.calendar.model.Event;

import java.util.ArrayList;
import java.util.List;

public class AcquistoMapper {

    public static AcquistoEntity getEntity(AcquistoDto dto , Event eventCalendar){
        AcquistoEntity acquistoEntity = new AcquistoEntity();
        acquistoEntity.setProdotto(ProdottoMapper.getEntity(dto.getProdotto()));
        acquistoEntity.setUtente(UtenteMapper.getEntity(dto.getUtente() , null));
        acquistoEntity.setQuantita(dto.getQuantita());
        EventoDto datiEventoDto = dto.getEvento();
        if(datiEventoDto != null){
            EventoEntity datiEventoEntity = new EventoEntity();
            datiEventoEntity.setId(datiEventoDto.getId());
            datiEventoEntity.setDataInizio(datiEventoDto.getDataInizio());
            datiEventoEntity.setDataFine(datiEventoDto.getDataFine());
            datiEventoEntity.setIdEvent(datiEventoDto.getIdEvent());
            acquistoEntity.setEvento(datiEventoEntity);
        }
        if(eventCalendar != null){
            acquistoEntity.getEvento().setIdEvent(eventCalendar.getId());
        }

        return acquistoEntity;
    }

    public static AcquistoDto getDTO(AcquistoEntity entity){
        AcquistoDto dto = new AcquistoDto();
        dto.setDataAcquisto(entity.getDataAcquisto());
        dto.setProdotto(ProdottoMapper.getDTO(entity.getProdotto()));
        dto.setUtente(UtenteMapper.getDTO(entity.getUtente()));
        dto.setQuantita(entity.getQuantita());
        dto.setId(entity.getId());
        EventoEntity datiEventoEntity = entity.getEvento();
        if(datiEventoEntity != null){
            EventoDto datiEventoDto = new EventoDto();
            datiEventoDto.setId(datiEventoEntity.getId());
            datiEventoDto.setDataInizio(datiEventoEntity.getDataInizio());
            datiEventoDto.setDataFine(datiEventoEntity.getDataFine());
            datiEventoDto.setIdEvent(datiEventoEntity.getIdEvent());
            dto.setEvento(datiEventoDto);
        }
        return dto;
    }

    public static List<AcquistoDto> getListDTO(List<AcquistoEntity> listEntity){
        List<AcquistoDto> out = new ArrayList<>();
        if(listEntity != null){
            for (AcquistoEntity entity:listEntity) {
                out.add(getDTO(entity));
            }
        }
        return out;
    }
}
