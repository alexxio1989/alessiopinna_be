package com.example.alessiopinnabe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tpl_utente")
@Getter
@Setter
public class TplUtenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tpl_utente", nullable = false)
    private Integer id;

    @Column(name = "codice", length = 45)
    private String codice;

    @Column(name = "descrizione", length = 45)
    private String descrizione;

}