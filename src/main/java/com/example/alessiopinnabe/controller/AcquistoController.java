package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.*;
import com.example.alessiopinnabe.dto.request.RequestCarrelloDto;
import com.example.alessiopinnabe.dto.response.ResponseAcquistoDto;
import com.example.alessiopinnabe.service.ServiceAcquisto;
import com.example.alessiopinnabe.util.Constants;
import com.example.alessiopinnabe.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.Map;

@RestController
@RequestMapping("acquisto")
public class AcquistoController {

    @Autowired
    private ServiceAcquisto serviceAcquisto;

    @PostMapping
    public ResponseAcquistoDto save(@Nullable @RequestHeader(value="token-google") String tokenString , @RequestBody RequestCarrelloDto carrello) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TokenDto tokenResponseDto = mapper.readValue(tokenString, TokenDto.class);
        return serviceAcquisto.saveCarrello(carrello, tokenResponseDto);
    }

    @DeleteMapping
    public ResponseAcquistoDto delete(@RequestHeader(value="token-google") String tokenString , @RequestBody AcquistoDto prenotazione) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TokenDto tokenResponseDto = mapper.readValue(tokenString, TokenDto.class);
        return serviceAcquisto.delete(prenotazione, tokenResponseDto);
    }

    @GetMapping("/{idUtente}")
    @CrossOrigin(origins = "*")
    public ResponseAcquistoDto getAll(@RequestHeader(value="token-google") String tokenString , @PathVariable String idUtente) throws JsonProcessingException {
        return serviceAcquisto.getAll(idUtente);
    }



}
