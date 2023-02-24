package com.example.alessiopinnabe.dto;

import lombok.Getter;

@Getter
public enum TypeServizio {
    EVENTO("EVENTO"),
    PRODOTTO("PRODOTTO");

    private String code;

    TypeServizio(String code) {
        this.code = code;
    }
}
