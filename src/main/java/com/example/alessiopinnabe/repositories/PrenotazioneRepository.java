package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.PrenotazioneEntity;
import com.example.alessiopinnabe.entity.PrenotazioneIdEntity;
import com.example.alessiopinnabe.entity.UtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<PrenotazioneEntity, PrenotazioneIdEntity> {

    @Query(value = "SELECT * FROM prenotazione WHERE utenteIdutente=:idIutente", nativeQuery = true)
    List<PrenotazioneEntity> getPrenotazioniByUtente(@Param("idIutente") Integer idIutente);
}