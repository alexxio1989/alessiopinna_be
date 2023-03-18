package com.example.alessiopinnabe.mapper.mapstruct;

import com.example.alessiopinnabe.dto.ImageDto;
import com.example.alessiopinnabe.entity.ImgServizio;
import com.example.alessiopinnabe.entity.Servizio;
import org.apache.commons.collections4.ListUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ImagesMapper {

    @Mapping(target = "servizio" , source = "servizio")
    @Mapping(target = "id" , source = "dto.id")
    @Mapping(target = "base64" , source = "dto.base64")
    ImgServizio toEntity(ImageDto dto , Servizio servizio);


    default List<ImgServizio> toListEntity(List<ImageDto> listDto , Servizio servizio) {
        List<ImgServizio> out = new ArrayList<>();
        if(listDto != null){
            for (ImageDto imageDto:listDto) {
                out.add(toEntity(imageDto,servizio));
            }
        }
        return out;
    }
}
