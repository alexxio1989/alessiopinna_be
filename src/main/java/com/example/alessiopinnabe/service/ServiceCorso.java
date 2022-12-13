package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.Corso;
import com.example.alessiopinnabe.dto.ResponseCorso;
import com.example.alessiopinnabe.mapper.CorsoMapper;
import com.example.alessiopinnabe.repositories.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceCorso {

    @Autowired
    private CorsoRepository corsoRepository;


    @Transactional
    public ResponseCorso getCorsi(){
        ResponseCorso out = new ResponseCorso();
        List<com.example.alessiopinnabe.entity.Corso> all = corsoRepository.findAll();

        List<Corso> corsiDTO = CorsoMapper.getCorsiDTO(all);
        out.setCorsi(corsiDTO);
        return out;
    }

    @Transactional
    public ResponseCorso save(Corso corso){
        com.example.alessiopinnabe.entity.Corso corsoEntity = CorsoMapper.getEntity(corso);
        corsoRepository.save(corsoEntity);
        return getCorsi();
    }

}
