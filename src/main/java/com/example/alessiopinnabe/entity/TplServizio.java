package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tpl_servizio")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TplServizio {
    @Id
    @Column(name = "codice", nullable = false, length = 100)
    private String codice;

    @Column(name = "descrizione")
    private String descrizione;

}