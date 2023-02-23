package com.example.alessiopinnabe.handlers;

import com.example.alessiopinnabe.dto.core.ErrorResponseDto;
import com.example.alessiopinnabe.exceptions.CoreException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionsHandler {

    public ResponseEntity<ErrorResponseDto> handleRequestException(HttpServletRequest request,
                                                                   Exception ex){
        return serviceHandler(ex);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> serviceHandler(Throwable throwable){
        if(throwable instanceof CoreException){
            CoreException coreException = (CoreException) throwable;
            return getResponse(coreException.getStatus().value(), coreException.getMessage());
        } else {
            int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            String error = throwable.getMessage();
            return getResponse(code, error);
        }
    }

    private ResponseEntity getResponse(int code, String error) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setCode(code);
        errorResponseDto.setError(error);
        errorResponseDto.setSuccess(false);
        return ResponseEntity.status(errorResponseDto.getCode()).body(errorResponseDto);
    }
}
