package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.config.JwtProvider;
import com.example.alessiopinnabe.dto.TokenDto;
import com.example.alessiopinnabe.dto.UtenteDtoFull;
import com.example.alessiopinnabe.dto.request.RequestLoginDto;
import com.example.alessiopinnabe.dto.response.ResponseServiziDto;
import com.example.alessiopinnabe.dto.response.ResponseUtenteDto;
import com.example.alessiopinnabe.dto.UtenteDto;
import com.example.alessiopinnabe.entity.Token;

import com.example.alessiopinnabe.entity.TplUtente;

import com.example.alessiopinnabe.entity.Utente;

import com.example.alessiopinnabe.exceptions.CoreException;
import com.example.alessiopinnabe.mapper.mapstruct.TokenMapper;
import com.example.alessiopinnabe.mapper.mapstruct.UtenteMapper;
import com.example.alessiopinnabe.repositories.TplUtenteRepository;
import com.example.alessiopinnabe.repositories.TokenRepository;
import com.example.alessiopinnabe.repositories.UtenteRepository;
import com.example.alessiopinnabe.service.core.CrudService;
import com.example.alessiopinnabe.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.oauth2.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceUtente implements CrudService<RequestLoginDto,ResponseUtenteDto> {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private TplUtenteRepository tplUtenteEntityRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UtenteMapper utenteMapper;
    @Autowired
    private TokenMapper tokenMapper;

    @Transactional
    @Override
    public ResponseEntity<ResponseUtenteDto> save(RequestLoginDto req, TokenDto tokenDto){
        ResponseUtenteDto out = new ResponseUtenteDto();
        try {
            long emailUsed = utenteRepository.countEmail(req.getUtente().getEmail());
            if(emailUsed == 0){
                Utente entity = utenteMapper.getEntity(req.getUtente(), req.getPassword());
                TplUtente userTpl = tplUtenteEntityRepository.getUSER();
                entity.setTipoUtente(userTpl);
                Utente utenteSaved = utenteRepository.save(entity);
                out.setUtente(utenteMapper.getDto(utenteSaved));
            } else {
                throw new CoreException("Email usata non disponibile",HttpStatus.CONFLICT , null);
            }

        } catch (DataAccessException ex){
            throw new CoreException("Errore durante la signin utente",HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());

        }
        ResponseEntity<ResponseUtenteDto> response = new ResponseEntity<>(out, HttpStatus.OK);

        return response;
    }

    @Transactional
    @Override
    public ResponseEntity<ResponseUtenteDto> get(RequestLoginDto req, TokenDto tokenDto){
        ResponseUtenteDto out = new ResponseUtenteDto();
        try {
            Utente utenteSaved = utenteRepository.findByEmailAndPassword(req.getUtente().getEmail(), req.getPassword());
            if(utenteSaved != null){
                UtenteDto utenteDTO = utenteMapper.getDto(utenteSaved);
                out.setUtente(utenteDTO);
            } else {
                throw new CoreException("Utente o password non corretti",HttpStatus.BAD_REQUEST , null);
            }

        } catch (DataAccessException ex){
            throw new CoreException("Errore durante la login utente",HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());

        }
        ResponseEntity<ResponseUtenteDto> response = new ResponseEntity<>(out, HttpStatus.OK);
        return response;
    }


    @Transactional
    public ResponseEntity<ResponseUtenteDto> loginAdmin(RequestLoginDto req){
        ResponseUtenteDto out = new ResponseUtenteDto();
        try {
            Utente utenteSaved = utenteRepository.findByEmailAndPassword(req.getUtente().getEmail(), req.getPassword());
            if(utenteSaved != null){
                UtenteDto utenteDTO = utenteMapper.getDto(utenteSaved);
                Token tokenEntityDefault = tokenRepository.getByProvidersAndUser(utenteSaved.getId(), Constants.DEFAULT);
                String tokenDefaultGenerated = tokenMapper.generateJWT(utenteDTO, utenteSaved.getPassword());
                Token newTokenEntityDefault = tokenMapper.fromDefaultToEntity(tokenDefaultGenerated, utenteSaved, tokenEntityDefault);
                tokenRepository.save(newTokenEntityDefault);
                out.setUtente(utenteDTO);
            } else {
                throw new CoreException("Utente o password non corretti",HttpStatus.BAD_REQUEST , null);
            }

        } catch (DataAccessException ex){
            throw new CoreException("Errore durante la login utente",HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage());
        }
        ResponseEntity<ResponseUtenteDto> response = new ResponseEntity<>(out, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<ResponseUtenteDto> delete(String id, TokenDto token) {
        throw new CoreException("Metodo non implementato",HttpStatus.METHOD_NOT_ALLOWED , null);
    }

    @Override
    public ResponseEntity<ResponseUtenteDto> getAll() {
        throw new CoreException("Metodo non implementato",HttpStatus.METHOD_NOT_ALLOWED , null);
    }

    @Override
    public ResponseEntity<ResponseUtenteDto> getAll(String id) {
        throw new CoreException("Metodo non implementato",HttpStatus.METHOD_NOT_ALLOWED , null);
    }

}
