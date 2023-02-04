package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "acquisto_evento")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AcquistoEvento extends _Acquisto {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Evento evento;

    @Column(name = "data_inizio")
    private Timestamp dataInizio;

    @Column(name = "data_fine")
    private Timestamp dataFine;

    @Column(name = "id_event_calendar")
    private String idEventCalendar;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "acquistoEvento")
    private DetailAcquistoEvento detailAcquistoEvento;

}