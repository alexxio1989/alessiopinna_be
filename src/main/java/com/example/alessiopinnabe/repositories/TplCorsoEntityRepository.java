package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.TplCorsoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TplCorsoEntityRepository extends JpaRepository<TplCorsoEntity, Integer> {
}