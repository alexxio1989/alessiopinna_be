package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.*;
import com.example.alessiopinnabe.service.ServiceAcquisto;
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
    private ServiceAcquisto prenotazioneService;

    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseAcquistoDto save(@RequestHeader(value="token-google") String tokenString , @RequestBody AcquistoDto prenotazione) throws JsonProcessingException {
        ResponseAcquistoDto out;
        ObjectMapper mapper = new ObjectMapper();
        TokenResponseDto tokenResponseDto = mapper.readValue(tokenString, TokenResponseDto.class);
        if(tokenResponseDto != null && !Util.isTmspExpired(tokenResponseDto.getDateExiration())){
            out = prenotazioneService.save(prenotazione, tokenResponseDto);
        } else {
            out = new ResponseAcquistoDto();
            out.setSuccess(false);
            out.setCode(999);
            out.setError("Token google assente o scaduto");
        }
        return out;
    }

    @PostMapping("/delete")
    @CrossOrigin(origins = "*")
    public ResponseAcquistoDto delete(@RequestHeader(value="token-google") String tokenString , @RequestBody AcquistoDto prenotazione) throws JsonProcessingException {
        ResponseAcquistoDto out;
        ObjectMapper mapper = new ObjectMapper();
        TokenResponseDto tokenResponseDto = mapper.readValue(tokenString, TokenResponseDto.class);
        if(tokenResponseDto != null && !Util.isTmspExpired(tokenResponseDto.getDateExiration())){
            out = prenotazioneService.delete(prenotazione, tokenResponseDto);
        } else {
            out = new ResponseAcquistoDto();
            out.setSuccess(false);
            out.setCode(999);
            out.setError("Token google assente o scaduto");
        }
        return out;
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseAcquistoDto getAll() {
        return prenotazioneService.getAll();
    }

    @GetMapping("/getAllByUtente/{idUtente}")
    @CrossOrigin(origins = "*")
    public ResponseAcquistoDto getAllByUtente(@RequestHeader Map<String, String> headers , @PathVariable Integer idUtente) throws JsonProcessingException {
        ResponseAcquistoDto out;
        ObjectMapper mapper = new ObjectMapper();
        String tokenString = headers.get("token-google");
        TokenResponseDto tokenResponseDto = mapper.readValue(tokenString, TokenResponseDto.class);
        if(tokenResponseDto != null && !Util.isTmspExpired(tokenResponseDto.getDateExiration())){
            out = prenotazioneService.getAllByUtente(idUtente, tokenResponseDto);
        } else {
            out = new ResponseAcquistoDto();
            out.setSuccess(false);
            out.setCode(999);
            out.setError("Token google assente o scaduto");
        }
        return out;
    }

    @GetMapping("/getAllByUtenteAndCorso/{idUtente}/{idCorso}")
    @CrossOrigin(origins = "*")
    public ResponseAcquistoDto getAllByUtenteAndCorso(@RequestHeader Map<String, String> headers, @PathVariable Integer idUtente , @PathVariable Integer idCorso) throws JsonProcessingException {
        ResponseAcquistoDto out;
        ObjectMapper mapper = new ObjectMapper();
        String tokenString = headers.get("token-google");
        TokenResponseDto tokenResponseDto = mapper.readValue(tokenString, TokenResponseDto.class);
        if(tokenResponseDto != null && !Util.isTmspExpired(tokenResponseDto.getDateExiration())){
            out = prenotazioneService.getAllByUtenteAndProdotto(idUtente,idCorso, tokenResponseDto);
        } else {
            out = new ResponseAcquistoDto();
            out.setCode(999);
            out.setError("Token google assente o scaduto");
        }
        return out;
    }


}
