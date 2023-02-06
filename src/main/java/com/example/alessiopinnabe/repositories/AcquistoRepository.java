package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity._Acquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquistoRepository extends JpaRepository<_Acquisto, Integer> {

}