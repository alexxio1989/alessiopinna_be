package com.example.alessiopinnabe.service;

import com.example.alessiopinnabe.dto.PrenotazioneDto;
import com.example.alessiopinnabe.dto.ResponsePrenotazioneDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PrenotazioneService {

    public ResponsePrenotazioneDto save(PrenotazioneDto prenotazione) {
        ResponsePrenotazioneDto out = new ResponsePrenotazioneDto();
        out.setSuccess(true);
        return out;
    }

    public ResponsePrenotazioneDto delete(PrenotazioneDto prenotazione) {
        ResponsePrenotazioneDto out = new ResponsePrenotazioneDto();
        out.setSuccess(true);
        return out;
    }
}
