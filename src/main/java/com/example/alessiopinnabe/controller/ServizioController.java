package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.EventoDto;
import com.example.alessiopinnabe.dto.ProdottoDto;
import com.example.alessiopinnabe.dto.response.ResponseServiziDto;
import com.example.alessiopinnabe.service.ServiceServizio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("servizio")
public class ServizioController {

    @Autowired
    private ServiceServizio serviceProdotto;

    @GetMapping
    public ResponseServiziDto getProdotti() {
        return serviceProdotto.getProdotti();
    }

    @PostMapping("/prodotto")
    public ResponseServiziDto saveProdotto(@RequestBody ProdottoDto dto) {
        return serviceProdotto.save(dto);
    }

    @PostMapping("/evento")
    public ResponseServiziDto saveEvento(@RequestBody EventoDto dto) {
        return serviceProdotto.save(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseServiziDto delete(@PathVariable String id) {
        return serviceProdotto.delete(id);
    }

}
