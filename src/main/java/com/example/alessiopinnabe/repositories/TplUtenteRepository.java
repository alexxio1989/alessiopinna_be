package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.TplUtente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TplUtenteRepository extends JpaRepository<TplUtente, Integer> {
    @Query(value = "SELECT * FROM tpl_utente WHERE codice='SU'", nativeQuery = true)
    TplUtente getSUPER_USER();

    @Query(value = "SELECT * FROM tpl_utente WHERE codice='U'", nativeQuery = true)
    TplUtente getUSER();
}