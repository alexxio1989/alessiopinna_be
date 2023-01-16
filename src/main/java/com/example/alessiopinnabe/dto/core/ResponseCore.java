package com.example.alessiopinnabe.dto.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseCore {
    private boolean success;
    private String error;
    private int code;
}
