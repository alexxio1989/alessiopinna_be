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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCorso {

    @Autowired
    private CorsoRepository corsoRepository;


    @Transactional
    public ResponseCorsoDto getCorsi(){
        ResponseCorsoDto out = new ResponseCorsoDto();
        try {
            List<CorsoEntity> all = corsoRepository.getAllEnabled();

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
    public ResponseCorsoDto getCorsiFull(){
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

    @Transactional
    public ResponseCorsoDto delete(Integer id) {
        ResponseCorsoDto out = new ResponseCorsoDto();
        try {
            int changed = 0;
            Optional<CorsoEntity> byId = corsoRepository.findById(id);
            if(byId.isPresent()){
                CorsoEntity corsoEntity = byId.get();
                corsoEntity.setEnable(0);
                corsoRepository.save(corsoEntity);
                changed = 1;
            }
            if(changed == 0){
                out.setSuccess(false);
                return out;
            }
        }catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }

        return getCorsi();
    }


}
