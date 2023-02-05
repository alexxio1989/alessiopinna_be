package com.example.alessiopinnabe.mapper.mapstruct.util;

import com.example.alessiopinnabe.dto.ProdottoDto;
import com.example.alessiopinnabe.entity.Evento;
import com.example.alessiopinnabe.entity.Prodotto;
import com.example.alessiopinnabe.entity.Servizio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServizioUtil {

    public static List<Prodotto> getProdotti(List<Servizio> entityList){
        return (List<Prodotto>)(List<?>) entityList.stream().filter(s-> s instanceof Prodotto).collect(Collectors.toList());
    }

    public static List<Evento> getEventi(List<Servizio> entityList){
        return (List<Evento>)(List<?>) entityList.stream().filter(s-> s instanceof Evento).collect(Collectors.toList());
    }
}
