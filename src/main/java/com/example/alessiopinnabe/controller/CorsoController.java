package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.CorsoDto;
import com.example.alessiopinnabe.dto.ResponseCorsoDto;
import com.example.alessiopinnabe.service.ServiceCorso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("corso")
@CrossOrigin(origins = "*")
public class CorsoController {

    @Autowired
    private ServiceCorso serviceCorso;

    @GetMapping("/all/{full}")
    @CrossOrigin(origins = "*")
    public ResponseCorsoDto getCorsi(@PathVariable Integer full) {

        if(full == 0){
            return serviceCorso.getCorsi();
        } else {
            return serviceCorso.getCorsiFull();
        }

    }

    @PostMapping("/save")
    @CrossOrigin(origins = "*")
    public ResponseCorsoDto save(@RequestBody CorsoDto corso) {
        return serviceCorso.save(corso);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "*")
    public ResponseCorsoDto delete(@PathVariable Integer id) {
        return serviceCorso.delete(id);
    }

}
