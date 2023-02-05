package com.example.alessiopinnabe.mapper.mapstruct;

import com.example.alessiopinnabe.dto.AcquistoEventoDto;
import com.example.alessiopinnabe.dto.AcquistoProdottoDto;
import com.example.alessiopinnabe.dto.ResponseAcquistoDto;
import com.example.alessiopinnabe.entity.AcquistoEvento;
import com.example.alessiopinnabe.entity.AcquistoProdotto;
import com.example.alessiopinnabe.entity._Acquisto;
import com.example.alessiopinnabe.mapper.mapstruct.util.AcquistoUtil;
import com.example.alessiopinnabe.mapper.mapstruct.util.ServizioUtil;
import com.google.api.services.calendar.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper( imports = AcquistoUtil.class )
public interface AcquistoMapper {
    @Mapping(target = "idEventCalendar" , source = "idEventCalendar")
    AcquistoEvento getAcquistoEventoEntity(AcquistoEventoDto dto, String idEventCalendar);
    AcquistoEvento getAcquistoEventoEntity(AcquistoEventoDto dto);
    AcquistoEventoDto getAcquistoEventoDto(AcquistoEvento entity);
    AcquistoProdotto getAcquistoProdottoEntity(AcquistoProdottoDto dto);
    AcquistoProdottoDto getAcquistoEventoDto(AcquistoProdotto entity);

    List<AcquistoProdottoDto> getAcquistoProdottoDtoList(List<AcquistoProdotto> entityList);
    List<AcquistoEventoDto> getAcquistoEventoDtoList(List<AcquistoEvento> entityList);
    @Mapping(target = "acquistoProdotti" , expression = "java(getAcquistoProdottoDtoList(AcquistoUtil.getAcquistiProdotti(entityList)))")
    @Mapping(target = "acquistoEventi" , expression = "java(getAcquistoEventoDtoList(AcquistoUtil.getAcquistiEventi(entityList)))")
    ResponseAcquistoDto getResponse(ResponseAcquistoDto input , List<_Acquisto> entityList);
}
