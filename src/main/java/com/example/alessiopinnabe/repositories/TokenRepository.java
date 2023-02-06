package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = "select * from token where id_utente=:idIutente and provider=:provider", nativeQuery = true)
    Token getByProvidersAndUser (@Param("idIutente") String idIutente , @Param("provider") String provider);
}