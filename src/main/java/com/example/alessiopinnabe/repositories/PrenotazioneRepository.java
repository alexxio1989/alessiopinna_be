package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.PrenotazioneEntity;
import com.example.alessiopinnabe.entity.PrenotazioneIdEntity;
import com.example.alessiopinnabe.entity.UtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<PrenotazioneEntity, PrenotazioneIdEntity> {

    @Query(value = "SELECT * FROM prenotazione WHERE utente_idutente=:idIutente and data_prenotazione >=:now", nativeQuery = true)
    List<PrenotazioneEntity> getPrenotazioniByUtente(@Param("idIutente") Integer idIutente , @Param("now") Timestamp now);

    @Query(value = "SELECT * FROM prenotazione WHERE utente_idutente=:idIutente and corso_idcorso=:idCorso and data_prenotazione >=:now", nativeQuery = true)
    List<PrenotazioneEntity> getPrenotazioniByUtenteAndCorso(@Param("idIutente") Integer idIutente , @Param("idCorso") Integer idCorso , @Param("now") Timestamp now);
}