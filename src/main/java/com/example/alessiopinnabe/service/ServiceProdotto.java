package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.EventoDto;
import com.example.alessiopinnabe.dto.ProdottoDto;
import com.example.alessiopinnabe.dto.ResponseServiziDto;
import com.example.alessiopinnabe.dto.ServizioDto;
import com.example.alessiopinnabe.entity.Prodotto;
import com.example.alessiopinnabe.entity.Servizio;
import com.example.alessiopinnabe.mapper.mapstruct.ServizioMapper;
import com.example.alessiopinnabe.repositories.ServizioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProdotto {

    @Autowired
    private ServizioRepository servizioRepository;
    @Autowired
    private ServizioMapper servizioMapper;

    @Transactional
    public ResponseServiziDto getProdotti(){
        ResponseServiziDto out = new ResponseServiziDto();
        try {
            List<Servizio> all = servizioRepository.findAll();
            servizioMapper.getResponse(out,all);
        }catch (DataAccessException ex){
            out.setSuccess(false);
            out.setError(ex.getMessage());
        }
        out.setSuccess(true);
        return out;
    }


    @Transactional
    public ResponseServiziDto save(ServizioDto servizioDto){

        Servizio servizio = null;
        try {
            if(servizioDto instanceof ProdottoDto){
                servizio = servizioMapper.getProdottoEntity((ProdottoDto) servizioDto);
            } else {
                servizio = servizioMapper.getEventoEntity((EventoDto) servizioDto);
            }
            servizioRepository.save(servizio);
        }catch (DataAccessException ex){
            ResponseServiziDto out = new ResponseServiziDto();
            out.setSuccess(false);
            out.setError(ex.getMessage());
            return out;
        }

        return getProdotti();
    }

    @Transactional
    public ResponseServiziDto delete(Integer id) {
        ResponseServiziDto out = new ResponseServiziDto();
        try {
            int changed = 0;
            Optional<Servizio> byId = servizioRepository.findById(id);
            if(byId.isPresent()){
                Servizio prodottoEntity = byId.get();
                prodottoEntity.setEnable(0);
                servizioRepository.save(prodottoEntity);
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
