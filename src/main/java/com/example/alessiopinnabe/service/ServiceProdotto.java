package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.ProdottoDto;
import com.example.alessiopinnabe.dto.ResponseProdottoDto;
import com.example.alessiopinnabe.entity.ProdottoEntity;
import com.example.alessiopinnabe.mapper.ProdottoMapper;
import com.example.alessiopinnabe.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProdotto {

    @Autowired
    private ProdottoRepository prodottoRepository;


    @Transactional
    public ResponseProdottoDto getProdotti(){
        ResponseProdottoDto out = new ResponseProdottoDto();
        try {
            List<ProdottoEntity> all = prodottoRepository.getAllEnabled();

            List<ProdottoDto> prodottiDTO = ProdottoMapper.getListDTO(all);
            out.setProdotti(prodottiDTO);
        }catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
        }
        out.setSuccess(true);
        return out;
    }

    @Transactional
    public ResponseProdottoDto getProdottiFull(){
        ResponseProdottoDto out = new ResponseProdottoDto();
        try {
            List<ProdottoEntity> all = prodottoRepository.findAll();

            List<ProdottoDto> prodottiDTO = ProdottoMapper.getListDTO(all);
            out.setProdotti(prodottiDTO);
        }catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
        }
        out.setSuccess(true);
        return out;
    }

    @Transactional
    public ResponseProdottoDto save(ProdottoDto prodottoDto){

        try {
            ProdottoEntity prodottoEntity = ProdottoMapper.getEntity(prodottoDto);
            prodottoRepository.save(prodottoEntity);
        }catch (DataAccessException ex){
            ResponseProdottoDto out = new ResponseProdottoDto();
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }

        return getProdotti();
    }

    @Transactional
    public ResponseProdottoDto delete(Integer id) {
        ResponseProdottoDto out = new ResponseProdottoDto();
        try {
            int changed = 0;
            Optional<ProdottoEntity> byId = prodottoRepository.findById(id);
            if(byId.isPresent()){
                ProdottoEntity prodottoEntity = byId.get();
                prodottoEntity.setEnable(0);
                prodottoRepository.save(prodottoEntity);
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

        return getProdotti();
    }


}
