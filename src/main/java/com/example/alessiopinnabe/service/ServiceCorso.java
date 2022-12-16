package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.CorsoDto;
import com.example.alessiopinnabe.dto.ResponseCorsoDto;
import com.example.alessiopinnabe.entity.CorsoEntity;
import com.example.alessiopinnabe.mapper.CorsoMapper;
import com.example.alessiopinnabe.repositories.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceCorso {

    @Autowired
    private CorsoRepository corsoRepository;


    @Transactional
    public ResponseCorsoDto getCorsi(){
        ResponseCorsoDto out = new ResponseCorsoDto();
        try {
            List<CorsoEntity> all = corsoRepository.findAll();

            List<CorsoDto> corsiDTO = CorsoMapper.getCorsiDTO(all);
            out.setCorsi(corsiDTO);
        }catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
        }
        out.setSuccess(true);
        return out;
    }

    @Transactional
    public ResponseCorsoDto save(CorsoDto corso){

        try {
            CorsoEntity corsoEntity = CorsoMapper.getEntity(corso);
            corsoRepository.save(corsoEntity);
        }catch (DataAccessException ex){
            ResponseCorsoDto out = new ResponseCorsoDto();
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }

        return getCorsi();
    }

}
