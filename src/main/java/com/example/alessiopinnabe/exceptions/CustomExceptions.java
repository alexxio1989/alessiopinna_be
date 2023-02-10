package com.example.alessiopinnabe.exceptions;

import javax.servlet.ServletException;

public class CustomExceptions extends ServletException {
    public Integer code;
    public CustomExceptions(String message, Integer code) {
        super(message);
        this.code = code;
    }
    public CustomExceptions(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }
}
