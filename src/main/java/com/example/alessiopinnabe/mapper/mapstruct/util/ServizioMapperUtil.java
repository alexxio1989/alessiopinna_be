package com.example.alessiopinnabe.mapper.mapstruct.util;

import com.example.alessiopinnabe.entity.Evento;
import com.example.alessiopinnabe.entity.Prodotto;
import com.example.alessiopinnabe.entity.Servizio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServizioMapperUtil {

    public static List<Prodotto> getProdotti(List<Servizio> entityList){
        List<Prodotto> out = new ArrayList<>();
        out = (List<Prodotto>)(List<?>) entityList.stream().filter(s-> s instanceof Prodotto).collect(Collectors.toList());
        return out;
    }

    public static List<Evento> getEventi(List<Servizio> entityList){
        List<Evento> out = new ArrayList<>();
        out = (List<Evento>)(List<?>) entityList.stream().filter(s-> s instanceof Evento).collect(Collectors.toList());
        return out;
    }
}
