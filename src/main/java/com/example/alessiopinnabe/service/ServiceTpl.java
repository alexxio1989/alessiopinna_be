package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.DominioDto;
import com.example.alessiopinnabe.dto.ServizioDto;
import com.example.alessiopinnabe.dto.TokenDto;
import com.example.alessiopinnabe.dto.response.ResponseServiziDto;
import com.example.alessiopinnabe.entity.TplServizio;
import com.example.alessiopinnabe.exceptions.CoreException;
import com.example.alessiopinnabe.mapper.mapstruct.TplMapper;
import com.example.alessiopinnabe.repositories.TplServizioRepository;
import com.example.alessiopinnabe.service.core.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTpl implements CrudService<DominioDto, List<DominioDto>> {

    @Autowired
    private TplServizioRepository tplServizioRepository;
    @Autowired
    private TplMapper tplMapper;

    @Override
    public ResponseEntity<List<DominioDto>> save(DominioDto dominio, TokenDto token){

        try {
            TplServizio entity = tplMapper.getEntity(dominio);
            tplServizioRepository.save(entity);
        } catch (DataAccessException ex){
            throw new CoreException("Errore durante il salvataggio del tipo servizio",HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
        }

        return getAll();
    }

    @Override
    public ResponseEntity<List<DominioDto>> delete(String id, TokenDto token) {
        return null;
    }

    public ResponseEntity<List<DominioDto>> getAll(){
        List<DominioDto> out = null;
        try {
            List<TplServizio> all = tplServizioRepository.findAll();
            out = tplMapper.getDtoList(all);
        } catch (DataAccessException ex){
            throw new CoreException("Errore durante il recupero dei tipi servizio",HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
        }
        ResponseEntity<List<DominioDto>> response = new ResponseEntity<>(out, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<List<DominioDto>> getAll(String id) {
        return null;
    }

    @Override
    public ResponseEntity<List<DominioDto>> get(DominioDto request, TokenDto token) {
        throw new CoreException("Metodo non implementato",HttpStatus.METHOD_NOT_ALLOWED , null);
    }


}
