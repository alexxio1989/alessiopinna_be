package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.AcquistoDto;
import com.example.alessiopinnabe.dto.DatiEventoDto;
import com.example.alessiopinnabe.entity.AcquistoEntity;
import com.example.alessiopinnabe.entity.DatiEventoEntity;

import java.util.ArrayList;
import java.util.List;

public class AcquistoMapper {

    public static AcquistoEntity getEntity(AcquistoDto dto){
        AcquistoEntity acquistoEntity = new AcquistoEntity();
        acquistoEntity.setProdotto(ProdottoMapper.getEntity(dto.getProdotto()));
        acquistoEntity.setUtente(UtenteMapper.getEntity(dto.getUtente() , null));
        acquistoEntity.setQuantita(dto.getQuantita());
        DatiEventoDto datiEventoDto = dto.getDatiEvento();
        if(datiEventoDto != null){
            DatiEventoEntity datiEventoEntity = new DatiEventoEntity();
            datiEventoEntity.setId(datiEventoDto.getId());
            datiEventoEntity.setDataInizio(datiEventoDto.getDataInizio());
            datiEventoEntity.setDataFine(datiEventoDto.getDataFine());
            datiEventoEntity.setIdEvent(datiEventoDto.getIdEvent());
            acquistoEntity.setDatiEvento(datiEventoEntity);
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
        DatiEventoEntity datiEventoEntity = entity.getDatiEvento();
        if(datiEventoEntity != null){
            DatiEventoDto datiEventoDto = new DatiEventoDto();
            datiEventoDto.setId(datiEventoEntity.getId());
            datiEventoDto.setDataInizio(datiEventoEntity.getDataInizio());
            datiEventoDto.setDataFine(datiEventoEntity.getDataFine());
            datiEventoDto.setIdEvent(datiEventoEntity.getIdEvent());
            dto.setDatiEvento(datiEventoDto);
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
