package com.example.alessiopinnabe.mapper.mapstruct;

import com.example.alessiopinnabe.dto.DominioDto;
import com.example.alessiopinnabe.entity.TplServizio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TplMapper {
    TplServizio getEntity(DominioDto dto);
    DominioDto getDto(TplServizio entity);
    List<TplServizio> getEntityList(List<DominioDto> dtoList);
    List<DominioDto> getDtoList(List<TplServizio> entity);
}
