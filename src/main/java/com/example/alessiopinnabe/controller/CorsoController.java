package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.Corso;
import com.example.alessiopinnabe.dto.ResponseCorso;
import com.example.alessiopinnabe.service.ServiceCorso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("corso")
@CrossOrigin(origins = "*")
public class CorsoController {

    @Autowired
    private ServiceCorso serviceCorso;

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseCorso getCorsi() {
        return serviceCorso.getCorsi();
    }

    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseCorso save(@RequestBody Corso corso) {
        return serviceCorso.save(corso);
    }

}
