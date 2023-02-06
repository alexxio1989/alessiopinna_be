package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.Servizio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServizioRepository extends CrudRepository<Servizio, String> {
}