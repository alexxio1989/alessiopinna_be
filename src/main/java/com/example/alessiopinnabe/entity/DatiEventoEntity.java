package com.example.alessiopinnabe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "dati_evento")
@Getter
@Setter
public class DatiEventoEntity {
    @Id
    @Column(name = "id_dati_evento", nullable = false)
    private Integer id;

    @Column(name = "id_event", length = 45)
    private String idEvent;

    @Column(name = "data_inizio")
    private Timestamp dataInizio;

    @Column(name = "data_fine")
    private Timestamp dataFine;


}