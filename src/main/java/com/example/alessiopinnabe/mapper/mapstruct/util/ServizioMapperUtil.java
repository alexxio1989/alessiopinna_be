package com.example.alessiopinnabe.mapper.mapstruct.util;

import com.example.alessiopinnabe.dto.ImageDto;
import com.example.alessiopinnabe.entity.Evento;
import com.example.alessiopinnabe.entity.ImgServizio;
import com.example.alessiopinnabe.entity.Prodotto;
import com.example.alessiopinnabe.entity.Servizio;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServizioMapperUtil {

    public static List<Prodotto> getProdotti(List<Servizio> entityList){
        List<Prodotto> out = new ArrayList<>();
        return (List<Prodotto>)(List<?>) entityList.stream().filter(s-> s instanceof Prodotto).collect(Collectors.toList());
    }

    public static List<Evento> getEventi(List<Servizio> entityList){
        List<Evento> out = new ArrayList<>();
        return (List<Evento>)(List<?>) entityList.stream().filter(s-> s instanceof Evento).collect(Collectors.toList());
    }

    private static List<ImgServizio> getDefaultImages(Servizio servizio){
        List<ImgServizio> out = new ArrayList<>();
        if(CollectionUtils.isEmpty(servizio.getImages())){
            ImgServizio img = new ImgServizio();
            img.setUrl("default");
            out.add(img);
        } else {
           return servizio.getImages();
        }
        return out;
    }
}
