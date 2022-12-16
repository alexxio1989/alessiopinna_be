package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.TplUtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TplUtenteEntityRepository extends JpaRepository<TplUtenteEntity, Integer> {
}