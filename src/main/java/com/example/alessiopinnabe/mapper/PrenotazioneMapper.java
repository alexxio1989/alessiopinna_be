package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.PrenotazioneDto;
import com.example.alessiopinnabe.entity.PrenotazioneEntity;
import com.example.alessiopinnabe.entity.PrenotazioneIdEntity;

import java.util.ArrayList;
import java.util.List;

public class PrenotazioneMapper {

    public static PrenotazioneEntity getEntity(PrenotazioneDto dto){
        PrenotazioneIdEntity in = new PrenotazioneIdEntity();
        in.setCorsoIdcorso(dto.getCorso().getId());
        in.setUtenteIdutente(dto.getUtente().getId());
        PrenotazioneEntity prenotazioneEntity = new PrenotazioneEntity();
        prenotazioneEntity.setId(in);
        return prenotazioneEntity;
    }

    public static PrenotazioneDto getDTO(PrenotazioneEntity entity){
        PrenotazioneDto dto = new PrenotazioneDto();
        dto.setDataPrenotazione(entity.getDataPrenotazione());
        dto.setCorso(CorsoMapper.getDTO(entity.getCorsoIdcorso()));
        dto.setUtente(UtenteMapper.getDTO(entity.getUtenteIdutente()));
        dto.setQntOre(entity.getQntOre());
        dto.setId(entity.getDataPrenotazione().getTime()+entity.getId().getCorsoIdcorso().intValue()+entity.getId().getUtenteIdutente().intValue());
        return dto;
    }

    public static List<PrenotazioneDto> getListDTO(List<PrenotazioneEntity> listEntity){
        List<PrenotazioneDto> out = new ArrayList<>();
        if(listEntity != null){
            for (PrenotazioneEntity entity:listEntity) {
                out.add(getDTO(entity));
            }
        }
        return out;
    }
}
