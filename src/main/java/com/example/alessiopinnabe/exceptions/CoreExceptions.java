package com.example.alessiopinnabe.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CoreExceptions extends RuntimeException{
    private HttpStatus status;
    private String message;

    public CoreExceptions(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
