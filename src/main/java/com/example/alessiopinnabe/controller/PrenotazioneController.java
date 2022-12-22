package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.CorsoDto;
import com.example.alessiopinnabe.dto.PrenotazioneDto;
import com.example.alessiopinnabe.dto.ResponseCorsoDto;
import com.example.alessiopinnabe.dto.ResponsePrenotazioneDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prenotazione")
@CrossOrigin(origins = "*")
public class PrenotazioneController {

    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponsePrenotazioneDto save(@RequestBody PrenotazioneDto prenotazione) {
        prenotazione.getDataPrenotazione();
        return null;
    }
}
