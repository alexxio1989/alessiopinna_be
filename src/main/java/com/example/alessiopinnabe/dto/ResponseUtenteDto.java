package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ResponseCore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUtenteDto extends ResponseCore {
    private UtenteDto utente;
}
