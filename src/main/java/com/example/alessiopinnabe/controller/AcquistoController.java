package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.*;
import com.example.alessiopinnabe.dto.request.RequestAcquistoDto;
import com.example.alessiopinnabe.dto.response.ResponseAcquistoDto;
import com.example.alessiopinnabe.service.ServiceAcquisto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;

@RestController
@RequestMapping("acquisto")
public class AcquistoController {

    @Autowired
    private ServiceAcquisto serviceAcquisto;

    @PostMapping
    public ResponseEntity<ResponseAcquistoDto> save(@Nullable @RequestHeader(value="token-google") String tokenString , @RequestBody RequestAcquistoDto carrello) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TokenDto tokenResponseDto = mapper.readValue(tokenString, TokenDto.class);
        return serviceAcquisto.save(carrello, tokenResponseDto);
    }

    @DeleteMapping("/{idAcquisto}")
    public ResponseEntity<ResponseAcquistoDto> delete(@RequestHeader(value="token-google") String tokenString , @RequestParam String idAcquisto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TokenDto tokenResponseDto = mapper.readValue(tokenString, TokenDto.class);
        return serviceAcquisto.delete(idAcquisto, tokenResponseDto);
    }

    @GetMapping("/{idUtente}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ResponseAcquistoDto> getAll(@RequestHeader(value="token-google") String tokenString , @PathVariable String idUtente) throws JsonProcessingException {
        return serviceAcquisto.getAll(idUtente);
    }



}
