package com.example.alessiopinnabe.mapper.mapstruct;

import com.example.alessiopinnabe.dto.AcquistoEventoDto;
import com.example.alessiopinnabe.dto.AcquistoProdottoDto;
import com.example.alessiopinnabe.dto.ResponseAcquistoDto;
import com.example.alessiopinnabe.entity.AcquistoEvento;
import com.example.alessiopinnabe.entity.AcquistoProdotto;
import com.example.alessiopinnabe.entity._Acquisto;
import com.example.alessiopinnabe.mapper.mapstruct.util.AcquistoMapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper( imports = AcquistoMapperUtil.class )
public interface AcquistoMapper {
    @Mapping(target = "idEventCalendar" , source = "idEventCalendar")
    AcquistoEvento getAcquistoEventoEntity(AcquistoEventoDto dto, String idEventCalendar);
    AcquistoEvento getAcquistoEventoEntity(AcquistoEventoDto dto);
    AcquistoEventoDto getAcquistoEventoDto(AcquistoEvento entity);
    AcquistoProdotto getAcquistoProdottoEntity(AcquistoProdottoDto dto);
    AcquistoProdottoDto getAcquistoEventoDto(AcquistoProdotto entity);

    List<AcquistoProdottoDto> getAcquistoProdottoDtoList(List<AcquistoProdotto> entityList);
    List<AcquistoEventoDto> getAcquistoEventoDtoList(List<AcquistoEvento> entityList);
    @Mapping(target = "acquistoProdotti" , expression = "java(getAcquistoProdottoDtoList(AcquistoMapperUtil.getAcquistiProdotti(entityList)))")
    @Mapping(target = "acquistoEventi" , expression = "java(getAcquistoEventoDtoList(AcquistoMapperUtil.getAcquistiEventi(entityList)))")
    ResponseAcquistoDto getResponse(ResponseAcquistoDto input , List<_Acquisto> entityList);
}
