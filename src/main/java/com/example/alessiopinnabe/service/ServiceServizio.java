package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.EventoDto;
import com.example.alessiopinnabe.dto.ProdottoDto;
import com.example.alessiopinnabe.dto.TokenDto;
import com.example.alessiopinnabe.dto.request.RequestServizioDto;
import com.example.alessiopinnabe.dto.response.ResponseServiziDto;
import com.example.alessiopinnabe.dto.ServizioDto;
import com.example.alessiopinnabe.entity.Servizio;
import com.example.alessiopinnabe.exceptions.CoreException;
import com.example.alessiopinnabe.mapper.mapstruct.ServizioMapper;
import com.example.alessiopinnabe.repositories.ServizioRepository;
import com.example.alessiopinnabe.service.core.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ServiceServizio implements CrudService<RequestServizioDto,ResponseServiziDto>{

    @Autowired
    private ServizioRepository servizioRepository;
    @Autowired
    private ServizioMapper servizioMapper;

    @Transactional
    @Override
    public ResponseEntity<ResponseServiziDto> getAll(){

        ResponseServiziDto out = new ResponseServiziDto();
        try {
            Iterable<Servizio> all = servizioRepository.findAll();
            List<Servizio> collect = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
            out = servizioMapper.getResponse(out,collect);
        } catch (DataAccessException ex){
            throw new CoreException("Errore durante il recupero dei servizi",HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
        }
        ResponseEntity<ResponseServiziDto> response = new ResponseEntity<>(out,HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<ResponseServiziDto> getAll(String id) {
        throw new CoreException("Metodo non implementato",HttpStatus.METHOD_NOT_ALLOWED , null);
    }

    @Override
    public ResponseEntity<ResponseServiziDto> get(RequestServizioDto request, TokenDto token) {
        throw new CoreException("Metodo non implementato",HttpStatus.METHOD_NOT_ALLOWED , null);

    }


    @Transactional
    @Override
    public ResponseEntity<ResponseServiziDto> save(RequestServizioDto request, TokenDto token){

        Servizio servizio = null;
        try {
            request.getServizioSelected().setDataCreazione(new Date(Calendar.getInstance().getTime().getTime()));
            request.getServizioSelected().setEnable(1);
            servizio = servizioMapper.getEntity(request.getServizioSelected());
            servizioRepository.save(servizio);
        } catch (DataAccessException ex){
            throw new CoreException("Errore durante il salvataggio del servizio",HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
        }
        return getAll();
    }

    @Transactional
    @Override
    public ResponseEntity<ResponseServiziDto> delete(String id, TokenDto token) {
        ResponseServiziDto out = new ResponseServiziDto();
        try {
            Optional<Servizio> byId = servizioRepository.findById(id);
            if(byId.isPresent()){
                Servizio prodottoEntity = byId.get();
                prodottoEntity.setEnable(0);
                servizioRepository.save(prodottoEntity);
            } else {
                throw new CoreException("Non è stato possibile eliminare alcun servizio con ID : " + id ,HttpStatus.BAD_REQUEST , null);
            }
        }catch (DataAccessException ex){
            throw new CoreException("Errore durante l'eliminazione del servizio",HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
        }

        return getAll();
    }


}
