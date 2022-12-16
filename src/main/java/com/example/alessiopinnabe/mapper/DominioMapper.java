package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.DominioDto;
import com.example.alessiopinnabe.entity.TplCorsoEntity;
import com.example.alessiopinnabe.entity.TplUtenteEntity;

import java.util.ArrayList;
import java.util.List;

public class DominioMapper {

    public static List<DominioDto> getListTipoCorsoDTO(List<TplCorsoEntity> listTplCorso){
        List<DominioDto> out = new ArrayList<>();
        if(listTplCorso != null){
            listTplCorso.forEach(t -> out.add(getTipoCorsoDTO(t)) );
        }
        return out;
    }

    public static List<DominioDto> getListTipoUtenteDTO(List<TplUtenteEntity> listTplUtente){
        List<DominioDto> out = new ArrayList<>();
        if(listTplUtente != null){
            listTplUtente.forEach(t -> out.add(getTipoUtenteDTO(t)) );
        }
        return out;
    }
    public static DominioDto getTipoCorsoDTO(TplCorsoEntity tplCorso){
        DominioDto out = new DominioDto();
        out.setId(tplCorso.getId());
        out.setCodice(tplCorso.getCodice());
        out.setDescrizione(tplCorso.getDescrizione());
        return out;
    }

    public static TplCorsoEntity getTipoCorsoEntity(DominioDto dominio){
        TplCorsoEntity tplCorso = new TplCorsoEntity();
        tplCorso.setId(dominio.getId());
        tplCorso.setCodice(dominio.getCodice());
        tplCorso.setDescrizione(dominio.getDescrizione());
        return tplCorso;
    }

    public static DominioDto getTipoUtenteDTO(TplUtenteEntity tplUtente){
        DominioDto out = new DominioDto();
        out.setId(tplUtente.getId());
        out.setCodice(tplUtente.getCodice());
        out.setDescrizione(tplUtente.getDescrizione());
        return out;
    }

    public static TplUtenteEntity getTipoUtenteEntity(DominioDto dominio){
        TplUtenteEntity tplUtente = new TplUtenteEntity();
        tplUtente.setId(dominio.getId());
        tplUtente.setCodice(dominio.getCodice());
        tplUtente.setDescrizione(dominio.getDescrizione());
        return tplUtente;
    }
}
