package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.AcquistoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AcquistoRepository extends JpaRepository<AcquistoEntity, Integer> {

    @Query(value = "SELECT * FROM acquisto WHERE id_utente=:idIutente and data_acquisto >=:now", nativeQuery = true)
    List<AcquistoEntity> getAcquistiByUtente(@Param("idIutente") Integer idIutente , @Param("now") Timestamp now);

    @Query(value = "SELECT * FROM acquisto WHERE id_utente=:idIutente and id_acquisto=:idAcquisto and data_acquisto >=:now", nativeQuery = true)
    List<AcquistoEntity> getAcquistiByUtenteAndProdotto(@Param("idIutente") Integer idIutente , @Param("idAcquisto") Integer idAcquisto , @Param("now") Timestamp now);
}