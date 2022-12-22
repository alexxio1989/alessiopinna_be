package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.CorsoDto;
import com.example.alessiopinnabe.dto.PrenotazioneDto;
import com.example.alessiopinnabe.dto.ResponseCorsoDto;
import com.example.alessiopinnabe.dto.ResponsePrenotazioneDto;
import com.example.alessiopinnabe.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prenotazione")
@CrossOrigin(origins = "*")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponsePrenotazioneDto save(@RequestBody PrenotazioneDto prenotazione) {
        return prenotazioneService.save(prenotazione);
    }

    @PostMapping("/delete")
    @CrossOrigin(origins = "*")
    public ResponsePrenotazioneDto delete(@RequestBody PrenotazioneDto prenotazione) {
        return prenotazioneService.delete(prenotazione);
    }
}
