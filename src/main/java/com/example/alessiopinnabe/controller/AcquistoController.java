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
@RequestMapping("acquisto")
@CrossOrigin(origins = "*")
public class AcquistoController {

    @Autowired
    private ServiceAcquisto serviceAcquisto;

    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseAcquistoDto save(@RequestHeader(value="token-google") String tokenString , @RequestBody AcquistoDto prenotazione) throws JsonProcessingException {
        ResponseAcquistoDto out;
        ObjectMapper mapper = new ObjectMapper();
        TokenResponseDto tokenResponseDto = mapper.readValue(tokenString, TokenResponseDto.class);
        if(tokenResponseDto != null && !Util.isTmspExpired(tokenResponseDto.getDateExiration())){
            out = serviceAcquisto.save(prenotazione, tokenResponseDto);
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
            out = serviceAcquisto.delete(prenotazione, tokenResponseDto);
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
        return serviceAcquisto.getAll();
    }

    @GetMapping("/getAll/{idUtente}")
    @CrossOrigin(origins = "*")
    public ResponseAcquistoDto getAllByUtente(@RequestHeader Map<String, String> headers , @PathVariable Integer idUtente) throws JsonProcessingException {
        ResponseAcquistoDto out;
        ObjectMapper mapper = new ObjectMapper();
        String tokenString = headers.get("token-google");
        TokenResponseDto tokenResponseDto = mapper.readValue(tokenString, TokenResponseDto.class);
        if(tokenResponseDto != null && !Util.isTmspExpired(tokenResponseDto.getDateExiration())){
            out = serviceAcquisto.getAllByUtente(idUtente, tokenResponseDto);
        } else {
            out = new ResponseAcquistoDto();
            out.setSuccess(false);
            out.setCode(999);
            out.setError("Token google assente o scaduto");
        }
        return out;
    }

    @GetMapping("/getAll/{idUtente}/{idProdotto}")
    @CrossOrigin(origins = "*")
    public ResponseAcquistoDto getAllByUtenteAndProdotto(@RequestHeader Map<String, String> headers, @PathVariable Integer idUtente , @PathVariable Integer idProdotto) throws JsonProcessingException {
        ResponseAcquistoDto out;
        ObjectMapper mapper = new ObjectMapper();
        String tokenString = headers.get("token-google");
        TokenResponseDto tokenResponseDto = mapper.readValue(tokenString, TokenResponseDto.class);
        if(tokenResponseDto != null && !Util.isTmspExpired(tokenResponseDto.getDateExiration())){
            out = serviceAcquisto.getAllByUtenteAndProdotto(idUtente,idProdotto, tokenResponseDto);
        } else {
            out = new ResponseAcquistoDto();
            out.setCode(999);
            out.setError("Token google assente o scaduto");
        }
        return out;
    }


}
