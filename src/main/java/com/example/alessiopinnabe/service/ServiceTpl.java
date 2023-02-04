package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.DominioDto;
import com.example.alessiopinnabe.entity.TplProdottoEntity;
import com.example.alessiopinnabe.entity.TplUtenteEntity;
import com.example.alessiopinnabe.mapper.DominioMapper;
import com.example.alessiopinnabe.repositories.TplServizioRepository;
import com.example.alessiopinnabe.repositories.TplUtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTpl {

    @Autowired
    private TplServizioRepository tplProdottoEntityRepository;
    @Autowired
    private TplUtenteRepository tplUtenteEntityRepository;

    public List<DominioDto> getTipiUtenti(){
        List<TplUtenteEntity> all = tplUtenteEntityRepository.findAll();
        return DominioMapper.getListTipoUtenteDTO(all);
    }

    public List<DominioDto> getTipiProdotti(){
        List<TplProdottoEntity> all = tplProdottoEntityRepository.findAll();
        return DominioMapper.getListTipoProdottiDTO(all);
    }

    public List<DominioDto> saveTplProdotto(DominioDto dominioDto){
        tplProdottoEntityRepository.save(DominioMapper.getTipoProdottoEntity(dominioDto));
        return getTipiProdotti();
    }

    public List<DominioDto> saveTplUtente(DominioDto dominioDto){
        tplUtenteEntityRepository.save(DominioMapper.getTipoUtenteEntity(dominioDto));
        return getTipiUtenti();
    }
}
