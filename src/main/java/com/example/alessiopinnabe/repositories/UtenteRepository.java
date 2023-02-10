package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {
    Optional<Utente> findByEmail(String email);
    Utente findByEmailAndPassword(String email , String password);

    @Query(value = "select count(*) from utente where email =:email", nativeQuery = true)
    long countEmail (@Param("email") String email);
}