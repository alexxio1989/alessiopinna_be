package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.*;
import com.example.alessiopinnabe.service.PrenotazioneService;
import com.example.alessiopinnabe.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("prenotazione")
@CrossOrigin(origins = "*")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponsePrenotazioneDto save(@RequestHeader(value="token-google") String tokenString , @RequestBody PrenotazioneDto prenotazione) throws JsonProcessingException {
        ResponsePrenotazioneDto out;
        ObjectMapper mapper = new ObjectMapper();
        TokenResponse tokenResponse = mapper.readValue(tokenString, TokenResponse.class);
        if(tokenResponse != null && !Util.isTmspExpired(tokenResponse.getDateExiration())){
            out = prenotazioneService.save(prenotazione, tokenResponse);
        } else {
            out = new ResponsePrenotazioneDto();
            out.setSuccess(false);
            out.setCode(999);
            out.setError("Token google assente o scaduto");
        }
        return out;
    }

    @PostMapping("/delete")
    @CrossOrigin(origins = "*")
    public ResponsePrenotazioneDto delete(@RequestHeader(value="token-google") String tokenString ,@RequestBody PrenotazioneDto prenotazione) throws JsonProcessingException {
        ResponsePrenotazioneDto out;
        ObjectMapper mapper = new ObjectMapper();
        TokenResponse tokenResponse = mapper.readValue(tokenString, TokenResponse.class);
        if(tokenResponse != null && !Util.isTmspExpired(tokenResponse.getDateExiration())){
            out = prenotazioneService.delete(prenotazione, tokenResponse);
        } else {
            out = new ResponsePrenotazioneDto();
            out.setSuccess(false);
            out.setCode(999);
            out.setError("Token google assente o scaduto");
        }
        return out;
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponsePrenotazioneDto getAll() {
        return prenotazioneService.getAll();
    }

    @GetMapping("/getAllByUtente/{idUtente}")
    @CrossOrigin(origins = "*")
    public ResponsePrenotazioneDto getAllByUtente(@RequestHeader Map<String, String> headers , @PathVariable Integer idUtente) throws JsonProcessingException {
        ResponsePrenotazioneDto out;
        ObjectMapper mapper = new ObjectMapper();
        String tokenString = headers.get("token-google");
        TokenResponse tokenResponse = mapper.readValue(tokenString, TokenResponse.class);
        if(tokenResponse != null && !Util.isTmspExpired(tokenResponse.getDateExiration())){
            out = prenotazioneService.getAllByUtente(idUtente,tokenResponse);
        } else {
            out = new ResponsePrenotazioneDto();
            out.setSuccess(false);
            out.setCode(999);
            out.setError("Token google assente o scaduto");
        }
        return out;
    }

    @GetMapping("/getAllByUtenteAndCorso/{idUtente}/{idCorso}")
    @CrossOrigin(origins = "*")
    public ResponsePrenotazioneDto getAllByUtenteAndCorso(@RequestHeader Map<String, String> headers, @PathVariable Integer idUtente , @PathVariable Integer idCorso) throws JsonProcessingException {
        ResponsePrenotazioneDto out;
        ObjectMapper mapper = new ObjectMapper();
        String tokenString = headers.get("token-google");
        TokenResponse tokenResponse = mapper.readValue(tokenString, TokenResponse.class);
        if(tokenResponse != null && !Util.isTmspExpired(tokenResponse.getDateExiration())){
            out = prenotazioneService.getAllByUtenteAndCorso(idUtente,idCorso,tokenResponse);
        } else {
            out = new ResponsePrenotazioneDto();
            out.setCode(999);
            out.setError("Token google assente o scaduto");
        }
        return out;
    }


}
