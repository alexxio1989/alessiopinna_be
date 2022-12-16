package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.CorsoDto;
import com.example.alessiopinnabe.dto.DominioDto;
import com.example.alessiopinnabe.dto.ResponseCorsoDto;
import com.example.alessiopinnabe.service.ServiceCorso;
import com.example.alessiopinnabe.service.TplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tpl")
@CrossOrigin(origins = "*")
public class TplController {

    @Autowired
    private TplService tplService;

    @GetMapping("/all/utente")
    @CrossOrigin(origins = "*")
    public List<DominioDto> allUtente() {
        return tplService.getTipiUtenti();
    }

    @GetMapping("/all/corsi")
    @CrossOrigin(origins = "*")
    public List<DominioDto> allCorsi() {
        return tplService.getTipiCorsi();
    }

    @PostMapping("/save/corso")
    @CrossOrigin(origins = "*")
    public List<DominioDto> saveTplCorso(@RequestBody DominioDto tpl) {
        return tplService.saveTplCorso(tpl);
    }

    @PostMapping("/save/utente")
    @CrossOrigin(origins = "*")
    public List<DominioDto> saveTplUtente(@RequestBody DominioDto tpl) {
        return tplService.saveTplUtente(tpl);
    }

}
