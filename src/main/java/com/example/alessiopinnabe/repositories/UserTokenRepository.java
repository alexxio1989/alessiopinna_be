package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.CorsoEntity;
import com.example.alessiopinnabe.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Integer> {

    @Query(value = "select * from user_token where utente_idutente=:idIutente and provider=:provider", nativeQuery = true)
    UserTokenEntity getByProvidersAndUser (@Param("idIutente") Integer idIutente , @Param("provider") String provider);
}