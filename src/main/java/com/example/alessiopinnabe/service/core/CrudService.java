package com.example.alessiopinnabe.service.core;

import com.example.alessiopinnabe.dto.TokenDto;
import org.springframework.http.ResponseEntity;

public interface CrudService <I,O>{
    ResponseEntity<O> save(I request , TokenDto token);
    ResponseEntity<O> delete(String id , TokenDto token);
    ResponseEntity<O> getAll();
    ResponseEntity<O> getAll(String id);
    ResponseEntity<O> get(I request , TokenDto token);
}
