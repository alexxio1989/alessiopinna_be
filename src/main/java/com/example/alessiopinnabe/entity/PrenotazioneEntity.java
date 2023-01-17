package com.example.alessiopinnabe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "prenotazione")
@Getter
@Setter
public class PrenotazioneEntity {
    @EmbeddedId
    private PrenotazioneIdEntity id;

    @MapsId("utenteIdutente")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_idutente", nullable = false)
    private UtenteEntity utenteIdutente;

    @MapsId("corsoIdcorso")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "corso_idcorso", nullable = false)
    private CorsoEntity corsoIdcorso;

    @Column(name = "qnt_ore")
    private BigDecimal qntOre;

    @Column(name = "data_prenotazione")
    private Timestamp dataPrenotazione;

    @Column(name = "id_event")
    private String idEvent;


}