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

    @GetMapping("/all/servizio")
    @CrossOrigin(origins = "*")
    public List<DominioDto> getTipiServizio() {
        return tplService.getTipiProdotti();
    }

    @PostMapping("/save/servizo")
    @CrossOrigin(origins = "*")
    public List<DominioDto> saveTplServizio(@RequestBody DominioDto tpl) {
        return tplService.saveTplProdotto(tpl);
    }


}
