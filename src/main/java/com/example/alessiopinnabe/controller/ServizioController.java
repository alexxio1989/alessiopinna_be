package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.EventoDto;
import com.example.alessiopinnabe.dto.ProdottoDto;
import com.example.alessiopinnabe.dto.ResponseServiziDto;
import com.example.alessiopinnabe.service.ServiceProdotto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("servizio")
@CrossOrigin(origins = "*")
public class ServizioController {

    @Autowired
    private ServiceProdotto serviceProdotto;

    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseServiziDto getProdotti(@PathVariable Integer full) {
        return serviceProdotto.getProdotti();
    }

    @PostMapping("/prodotto/save")
    @CrossOrigin(origins = "*")
    public ResponseServiziDto saveProdotto(@RequestBody ProdottoDto dto) {
        return serviceProdotto.save(dto);
    }

    @PostMapping("/evento/save")
    @CrossOrigin(origins = "*")
    public ResponseServiziDto saveEvento(@RequestBody EventoDto dto) {
        return serviceProdotto.save(dto);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "*")
    public ResponseServiziDto delete(@PathVariable Integer id) {
        return serviceProdotto.delete(id);
    }

}
