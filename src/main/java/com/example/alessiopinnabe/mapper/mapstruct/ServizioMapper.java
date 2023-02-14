package com.example.alessiopinnabe.mapper.mapstruct;

import com.example.alessiopinnabe.dto.EventoDto;
import com.example.alessiopinnabe.dto.ProdottoDto;
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

    ProdottoDto getProdottoDto(Prodotto entity);
    List<ProdottoDto> getProdottoDtoList(List<Prodotto> entityList);
    Prodotto getProdottoEntity(ProdottoDto dto);
    List<Prodotto> getProdottoEntityList(List<ProdottoDto> dtoList);
    EventoDto getEventoDto(Evento entity);
    List<EventoDto> getEventoDtoList(List<Evento> entityList);
    Evento getEventoEntity(EventoDto dto);
    List<Evento> getEventoEntityList(List<EventoDto> dtoList);

    @Mapping(target = "prodotti" , expression = "java(getProdottoDtoList(ServizioMapperUtil.getProdotti(entityList)))")
    @Mapping(target = "eventi" , expression = "java(getEventoDtoList(ServizioMapperUtil.getEventi(entityList)))")
    ResponseServiziDto getResponse(ResponseServiziDto input , List<Servizio> entityList);
}
