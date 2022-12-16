package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.PrenotazioneEntity;
import com.example.alessiopinnabe.entity.PrenotazioneIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends JpaRepository<PrenotazioneEntity, PrenotazioneIdEntity> {
}