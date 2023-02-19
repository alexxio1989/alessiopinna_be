package com.example.alessiopinnabe.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CoreException extends RuntimeException{
    private HttpStatus status;
    private String message;

    private String detailError;

    public CoreException(String message, HttpStatus status , String detailError) {
        super(message);
        this.message = message;
        this.status = status;
        this.detailError = detailError;
    }
}
