package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "acquisto_evento")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AttributeOverride(name = "id" , column = @Column(name = "id_acquisto_evento"))
public class AcquistoEvento extends _Acquisto {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id",referencedColumnName = "id", nullable = false ,insertable = false ,updatable = false)
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