package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "evento")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@PrimaryKeyJoinColumn(name = "id_servizio")
public class Evento extends Servizio{

    @Column(name = "data_inizio")
    private Timestamp dataInizio;

    @Column(name = "data_fine")
    private Timestamp dataFine;

}