package com.example.alessiopinnabe.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private Integer error;
    private String errorDescription;
}
