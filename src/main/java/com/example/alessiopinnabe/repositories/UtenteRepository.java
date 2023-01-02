package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.UtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<UtenteEntity, Integer> {
    @Query(value = "SELECT * FROM utente WHERE email=:email and password=:password", nativeQuery = true)
    UtenteEntity login(@Param("email") String email , @Param("password") String password );

    @Query(value = "select count(*) from utente where email =:email", nativeQuery = true)
    long countEmail (@Param("email") String email);
}