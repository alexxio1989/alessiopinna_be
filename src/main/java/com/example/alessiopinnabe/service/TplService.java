package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.DominioDto;
import com.example.alessiopinnabe.entity.TplCorsoEntity;
import com.example.alessiopinnabe.entity.TplUtenteEntity;
import com.example.alessiopinnabe.mapper.DominioMapper;
import com.example.alessiopinnabe.repositories.TplCorsoEntityRepository;
import com.example.alessiopinnabe.repositories.TplUtenteEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TplService {

    @Autowired
    private TplCorsoEntityRepository tplCorsoEntityRepository;
    @Autowired
    private TplUtenteEntityRepository tplUtenteEntityRepository;

    public List<DominioDto> getTipiUtenti(){
        List<TplUtenteEntity> all = tplUtenteEntityRepository.findAll();
        return DominioMapper.getListTipoUtenteDTO(all);
    }

    public List<DominioDto> getTipiCorsi(){
        List<TplCorsoEntity> all = tplCorsoEntityRepository.findAll();
        return DominioMapper.getListTipoCorsoDTO(all);
    }

    public List<DominioDto> saveTplCorso(DominioDto dominioDto){
        tplCorsoEntityRepository.save(DominioMapper.getTipoCorsoEntity(dominioDto));
        return getTipiCorsi();
    }

    public List<DominioDto> saveTplUtente(DominioDto dominioDto){
        tplUtenteEntityRepository.save(DominioMapper.getTipoUtenteEntity(dominioDto));
        return getTipiUtenti();
    }
}
