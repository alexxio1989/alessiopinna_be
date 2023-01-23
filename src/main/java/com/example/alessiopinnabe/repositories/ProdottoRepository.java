package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.ProdottoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<ProdottoEntity, Integer> {

    @Query(value = "select * from prodotto where enable = 1", nativeQuery = true)
    List<ProdottoEntity> getAllEnabled ();

}