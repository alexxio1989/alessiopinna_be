package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tpl_utente")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TplUtente implements Serializable {
    @Id
    @Column(name = "codice", nullable = false, length = 100)
    String codice;

    @Column(name = "descrizione", length = 45)
    String descrizione;

}