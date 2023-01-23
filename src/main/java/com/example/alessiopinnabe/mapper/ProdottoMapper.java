package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.ProdottoDto;
import com.example.alessiopinnabe.entity.ProdottoEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class ProdottoMapper {

    public static List<ProdottoDto> getListDTO(List<ProdottoEntity> entities){
        List<ProdottoDto> out = new ArrayList<>();
        if(entities != null){
            for (ProdottoEntity corsoEntity:entities) {
                out.add(getDTO(corsoEntity));
            }
        }
         return out;
    }

    public static ProdottoDto getDTO(ProdottoEntity entity){
        ProdottoDto out = new ProdottoDto();
        out.setId(entity.getId());
        out.setNome(entity.getNome());
        out.setNomeExt(entity.getNomeExt());
        out.setDescrizione(entity.getDescrizione());
        out.setTipo(DominioMapper.getTipoProdottoDTO(entity.getTplProdotto()));
        //out.setImgName(entity.getImgPrimaria());
        out.setPrezzo(entity.getPrezzo());
        return out;
    }

    public static ProdottoEntity getEntity(ProdottoDto dto){
        ProdottoEntity out = new ProdottoEntity();
        out.setId(dto.getId());
        out.setNome(dto.getNome());
        out.setNomeExt(dto.getNomeExt());
        out.setDescrizione(dto.getDescrizione());
        out.setTplProdotto(DominioMapper.getTipoProdottoEntity(dto.getTipo()));
        out.setPrezzo(dto.getPrezzo());
        out.setDataCreazione(new Date(System.currentTimeMillis()));
        out.setEnable(1);
        return out;
    }
}
