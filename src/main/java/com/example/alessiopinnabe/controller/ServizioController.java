package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.EventoDto;
import com.example.alessiopinnabe.dto.ProdottoDto;
import com.example.alessiopinnabe.dto.request.RequestServizioDto;
import com.example.alessiopinnabe.dto.response.ResponseServiziDto;
import com.example.alessiopinnabe.service.ServiceServizio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("servizio")
public class ServizioController {

    @Autowired
    private ServiceServizio serviceProdotto;

    @GetMapping
    public ResponseEntity<ResponseServiziDto> getAll() {
        return serviceProdotto.getAll();
    }

    @PostMapping
    public ResponseEntity<ResponseServiziDto> save(@RequestBody RequestServizioDto dto) {
        return serviceProdotto.save(dto,null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseServiziDto> delete(@PathVariable String id) {
        return serviceProdotto.delete(id,null);
    }

}
