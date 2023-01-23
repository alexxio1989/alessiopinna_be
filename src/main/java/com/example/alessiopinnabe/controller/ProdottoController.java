package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.ProdottoDto;
import com.example.alessiopinnabe.dto.ResponseProdottoDto;
import com.example.alessiopinnabe.service.ServiceProdotto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prodotto")
@CrossOrigin(origins = "*")
public class ProdottoController {

    @Autowired
    private ServiceProdotto serviceProdotto;

    @GetMapping("/all/{full}")
    @CrossOrigin(origins = "*")
    public ResponseProdottoDto getProdotti(@PathVariable Integer full) {

        if(full == 0){
            return serviceProdotto.getProdotti();
        } else {
            return serviceProdotto.getProdottiFull();
        }

    }

    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseProdottoDto save(@RequestBody ProdottoDto prodottoDto) {
        return serviceProdotto.save(prodottoDto);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "*")
    public ResponseProdottoDto delete(@PathVariable Integer id) {
        return serviceProdotto.delete(id);
    }

}
