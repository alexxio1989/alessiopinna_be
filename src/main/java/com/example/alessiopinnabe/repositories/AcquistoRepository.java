package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity._Acquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AcquistoRepository extends JpaRepository<_Acquisto, Integer> {

}