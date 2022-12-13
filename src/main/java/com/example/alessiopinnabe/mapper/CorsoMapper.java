package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.Corso;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class CorsoMapper {

    public static List<Corso> getCorsiDTO(List<com.example.alessiopinnabe.entity.Corso> corsiEntity){
        List<Corso> out = new ArrayList<>();
        if(corsiEntity != null){
            for (com.example.alessiopinnabe.entity.Corso corsoEntity:corsiEntity) {
                out.add(getDTO(corsoEntity));
            }
        }
         return out;
    }

    public static Corso getDTO(com.example.alessiopinnabe.entity.Corso entity){
        Corso out = new Corso();
        out.setId(entity.getId());
        out.setTitolo(entity.getTitolo());
        out.setDescrizione(entity.getDescrizione());
        out.setTipo(entity.getTipo());
        out.setImgName(entity.getImgName());
        out.setPrezzo(entity.getPrezzo());
        out.setGiorniOrari(entity.getGiorniOrari());
        return out;
    }

    public static com.example.alessiopinnabe.entity.Corso getEntity(Corso dto){
        com.example.alessiopinnabe.entity.Corso out = new com.example.alessiopinnabe.entity.Corso();
        out.setId(dto.getId());
        out.setTitolo(dto.getTitolo());
        out.setDescrizione(dto.getDescrizione());
        out.setTipo(dto.getTipo());
        out.setImgName(dto.getImgName());
        out.setPrezzo(dto.getPrezzo());
        out.setDataCreazione(new Date(System.currentTimeMillis()));
        out.setGiorniOrari(dto.getGiorniOrari());
        return out;
    }
}
