package com.example.alessiopinnabe.controller;

import com.example.alessiopinnabe.dto.DominioDto;
import com.example.alessiopinnabe.service.ServiceTpl;
import org.checkerframework.checker.units.qual.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated
@RequestMapping("tpl")
public class TplController {

    @Autowired
    private ServiceTpl tplService;

    @GetMapping
    public List<DominioDto> getTipiServizio() {
        return tplService.getTipiProdotti();
    }

    @PostMapping
    public List<DominioDto> saveTplServizio(@RequestBody @NotBlank @Valid DominioDto dominio) {
        return tplService.saveTplProdotto(dominio);
    }

}
