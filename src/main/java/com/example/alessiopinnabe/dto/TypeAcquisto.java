package com.example.alessiopinnabe.dto;

import lombok.Getter;

@Getter
public enum TypeAcquisto {
    ACQUISTO_EVENTO("ACQUISTO_EVENTO"),
    ACQUISTO_PRODOTTO("ACQUISTO_PRODOTTO");

    private String code;

    TypeAcquisto(String code) {
        this.code = code;
    }
}
