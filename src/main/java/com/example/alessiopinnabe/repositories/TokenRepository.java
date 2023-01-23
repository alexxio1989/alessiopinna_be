package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

    @Query(value = "select * from token where id_utente=:idIutente and provider=:provider", nativeQuery = true)
    TokenEntity getByProvidersAndUser (@Param("idIutente") Integer idIutente , @Param("provider") String provider);
}