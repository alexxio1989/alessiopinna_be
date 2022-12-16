package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.PrenotazioneEntity;
import com.example.alessiopinnabe.entity.TplUtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TplUtenteEntityRepository extends JpaRepository<TplUtenteEntity, Integer> {
    @Query(value = "SELECT * FROM tpl_utente WHERE codice='SU'", nativeQuery = true)
    TplUtenteEntity getSUPER_USER();

    @Query(value = "SELECT * FROM tpl_utente WHERE codice='U'", nativeQuery = true)
    TplUtenteEntity getUSER();
}