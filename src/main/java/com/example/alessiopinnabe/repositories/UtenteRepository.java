package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.UtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<UtenteEntity, Integer> {
}