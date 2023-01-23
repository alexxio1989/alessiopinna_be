package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.TplProdottoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TplProdottoEntityRepository extends JpaRepository<TplProdottoEntity, Integer> {
}