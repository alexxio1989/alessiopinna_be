package com.example.alessiopinnabe.repositories;

import com.example.alessiopinnabe.entity.Prenotazione;
import com.example.alessiopinnabe.entity.PrenotazioneId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, PrenotazioneId> {
}