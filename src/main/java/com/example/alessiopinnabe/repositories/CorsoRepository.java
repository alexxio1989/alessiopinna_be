package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, Integer> {
}