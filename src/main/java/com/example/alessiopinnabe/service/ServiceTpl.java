package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.DominioDto;
import com.example.alessiopinnabe.entity.TplServizio;
import com.example.alessiopinnabe.mapper.mapstruct.TplMapper;
import com.example.alessiopinnabe.repositories.TplServizioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTpl {

    @Autowired
    private TplServizioRepository tplServizioRepository;
    @Autowired
    private TplMapper tplMapper;

    public List<DominioDto> getTipiProdotti(){
        List<TplServizio> all = tplServizioRepository.findAll();
        return tplMapper.getDtoList(all);
    }

    public List<DominioDto> saveTplProdotto(DominioDto dominioDto){
        tplServizioRepository.save(tplMapper.getEntity(dominioDto));
        return getTipiProdotti();
    }

}
