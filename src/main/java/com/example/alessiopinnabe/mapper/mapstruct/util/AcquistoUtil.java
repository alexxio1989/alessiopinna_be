package com.example.alessiopinnabe.mapper.mapstruct.util;

import com.example.alessiopinnabe.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class AcquistoUtil {

    public static List<AcquistoProdotto> getAcquistiProdotti(List<_Acquisto> entityList){
        return (List<AcquistoProdotto>)(List<?>) entityList.stream().filter(a -> a instanceof AcquistoProdotto).collect(Collectors.toList());
    }

    public static List<AcquistoEvento> getAcquistiEventi(List<_Acquisto> entityList){
        return (List<AcquistoEvento>)(List<?>) entityList.stream().filter(a -> a instanceof AcquistoEvento).collect(Collectors.toList());
    }
}
