package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.TplServizio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TplServizioRepository extends JpaRepository<TplServizio, Integer> {
}