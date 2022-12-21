package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.CorsoDto;
import com.example.alessiopinnabe.entity.CorsoEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class CorsoMapper{

    public static List<CorsoDto> getCorsiDTO(List<CorsoEntity> corsiEntity){
        List<CorsoDto> out = new ArrayList<>();
        if(corsiEntity != null){
            for (CorsoEntity corsoEntity:corsiEntity) {
                out.add(getDTO(corsoEntity));
            }
        }
         return out;
    }

    public static CorsoDto getDTO(CorsoEntity entity){
        CorsoDto out = new CorsoDto();
        out.setId(entity.getId());
        out.setTitolo(entity.getTitolo());
        out.setTitoloExt(entity.getTitoloExt());
        out.setDescrizione(entity.getDescrizione());
        out.setTipo(DominioMapper.getTipoCorsoDTO(entity.getTplCorsoIdtplCorso()));
        out.setImgName(entity.getImgName());
        out.setPrezzo(entity.getPrezzo());
        out.setGiorniOrari(entity.getGiorniOrari());
        return out;
    }

    public static CorsoEntity getEntity(CorsoDto dto){
        CorsoEntity out = new CorsoEntity();
        out.setId(dto.getId());
        out.setTitolo(dto.getTitolo());
        out.setTitoloExt(dto.getTitoloExt());
        out.setDescrizione(dto.getDescrizione());
        out.setTplCorsoIdtplCorso(DominioMapper.getTipoCorsoEntity(dto.getTipo()));
        out.setImgName(dto.getImgName());
        out.setPrezzo(dto.getPrezzo());
        out.setDataCreazione(new Date(System.currentTimeMillis()));
        out.setGiorniOrari(dto.getGiorniOrari());
        return out;
    }
}
