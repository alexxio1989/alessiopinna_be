package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.DominioDto;
import com.example.alessiopinnabe.entity.TplProdottoEntity;
import com.example.alessiopinnabe.entity.TplUtenteEntity;

import java.util.ArrayList;
import java.util.List;

public class DominioMapper {

    public static List<DominioDto> getListTipoProdottiDTO(List<TplProdottoEntity> listTplprodotti){
        List<DominioDto> out = new ArrayList<>();
        if(listTplprodotti != null){
            listTplprodotti.forEach(t -> out.add(getTipoProdottoDTO(t)) );
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
    public static DominioDto getTipoProdottoDTO(TplProdottoEntity tplProdottoEntity){
        DominioDto out = new DominioDto();
        out.setId(tplProdottoEntity.getId());
        out.setCodice(tplProdottoEntity.getCodice());
        out.setDescrizione(tplProdottoEntity.getDescrizione());
        return out;
    }

    public static TplProdottoEntity getTipoProdottoEntity(DominioDto dominio){
        TplProdottoEntity tplProdotto = new TplProdottoEntity();
        tplProdotto.setId(dominio.getId());
        tplProdotto.setCodice(dominio.getCodice());
        tplProdotto.setDescrizione(dominio.getDescrizione());
        return tplProdotto;
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
