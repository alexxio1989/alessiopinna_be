package com.example.alessiopinnabe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "evento")
@Getter
@Setter
public class EventoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento", nullable = false)
    private Integer id;

    @Column(name = "id_event", nullable = false)
    private String idEvent;

    @Column(name = "data_inizio", nullable = false)
    private Timestamp dataInizio;

    @Column(name = "data_fine", nullable = false)
    private Timestamp dataFine;

    @Column(name = "ore", nullable = false)
    private Integer ore;
}