package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.DominioDto;
import com.example.alessiopinnabe.service.ServiceTpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tpl")
@CrossOrigin(origins = "*")
public class TplController {

    @Autowired
    private ServiceTpl tplService;

    @GetMapping("/all/utente")
    @CrossOrigin(origins = "*")
    public List<DominioDto> getTipiUtenti() {
        return tplService.getTipiUtenti();
    }

    @GetMapping("/all/prodotti")
    @CrossOrigin(origins = "*")
    public List<DominioDto> getTipiProdotti() {
        return tplService.getTipiProdotti();
    }

    @PostMapping("/save/prodotto")
    @CrossOrigin(origins = "*")
    public List<DominioDto> saveTplProdotto(@RequestBody DominioDto tpl) {
        return tplService.saveTplProdotto(tpl);
    }

    @PostMapping("/save/utente")
    @CrossOrigin(origins = "*")
    public List<DominioDto> saveTplUtente(@RequestBody DominioDto tpl) {
        return tplService.saveTplUtente(tpl);
    }

}
