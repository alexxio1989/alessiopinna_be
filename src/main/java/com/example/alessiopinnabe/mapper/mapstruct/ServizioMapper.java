package com.example.alessiopinnabe.mapper.mapstruct;

import com.example.alessiopinnabe.dto.EventoDto;
import com.example.alessiopinnabe.dto.ProdottoDto;
import com.example.alessiopinnabe.dto.ServizioDto;
import com.example.alessiopinnabe.dto.response.ResponseServiziDto;
import com.example.alessiopinnabe.entity.Evento;
import com.example.alessiopinnabe.entity.Prodotto;
import com.example.alessiopinnabe.entity.Servizio;
import com.example.alessiopinnabe.mapper.mapstruct.util.ServizioMapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper( imports = ServizioMapperUtil.class )
public interface ServizioMapper {

    default ServizioDto getDto(Servizio entity){
        ServizioDto dto = null;
        if(dto instanceof ProdottoDto){
            dto = getProdottoDto((Prodotto) entity);
        } else {
            dto = getEventoDto((Evento) entity);
        }
        return dto;
    }


    ProdottoDto getProdottoDto(Prodotto entity);
    EventoDto getEventoDto(Evento entity);


    default Servizio getEntity(ServizioDto dto){
        Servizio entity = null;
        if(dto instanceof ProdottoDto){
            entity = getProdottoEntity((ProdottoDto) dto);
        } else {
            entity = getEventoEntity((EventoDto) dto);
        }
        return entity;
    }

    Prodotto getProdottoEntity(ProdottoDto dto);
    Evento getEventoEntity(EventoDto dto);

    @Mapping(target = "prodotti" , expression = "java(getProdottoDtoList(ServizioMapperUtil.getProdotti(entityList)))")
    @Mapping(target = "eventi" , expression = "java(getEventoDtoList(ServizioMapperUtil.getEventi(entityList)))")
    ResponseServiziDto getResponse(ResponseServiziDto input , List<Servizio> entityList);

    List<ProdottoDto> getProdottoDtoList(List<Prodotto> entityList);

    List<EventoDto> getEventoDtoList(List<Evento> entityList);
}
