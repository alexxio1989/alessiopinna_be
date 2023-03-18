package com.example.alessiopinnabe.service;


import com.example.alessiopinnabe.dto.ImageDto;
import com.example.alessiopinnabe.dto.TokenDto;
import com.example.alessiopinnabe.dto.request.RequestServizioDto;
import com.example.alessiopinnabe.entity.ImgServizio;
import com.example.alessiopinnabe.entity.Servizio;
import com.example.alessiopinnabe.exceptions.CoreException;
import com.example.alessiopinnabe.mapper.mapstruct.ImagesMapper;
import com.example.alessiopinnabe.repositories.ImagesRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class ServiceImages {

    @Autowired
    private ImagesRepository jpaRepository;
    @Autowired
    private ImagesMapper mapper;


    public List<ImgServizio> deleteAndUpdate(List<ImageDto> images, Servizio servizio){
        List<ImgServizio> imgServizios = null;

        if(StringUtils.isNotEmpty(servizio.getId())){
            try {
                jpaRepository.deleteByServizio(servizio);
            } catch (DataAccessException ex){
                throw new CoreException("Errore durante l'eliminazione delle vecchie immagini del servizio", HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
            }
        }

        try {
            List<ImgServizio> entities = mapper.toListEntity(images, servizio);
            imgServizios = jpaRepository.saveAll(entities);
        } catch (DataAccessException ex){
            throw new CoreException("Errore durante il salvataggio delle immagini del servizio", HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
        }

        return imgServizios;
    }

    public long deleteByServizio( Servizio servizio){
        if(StringUtils.isNotEmpty(servizio.getId())){
            try {
                return jpaRepository.deleteByServizio(servizio);
            } catch (DataAccessException ex){
                throw new CoreException("Errore durante l'eliminazione delle vecchie immagini del servizio", HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
            }
        }
        return 0;
    }

}
