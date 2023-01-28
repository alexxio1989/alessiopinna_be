package com.example.alessiopinnabe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "utente")
@Getter
@Setter
public class UtenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utente", nullable = false)
    private Integer id;

    @Column(name = "anagrafica", length = 100)
    private String anagrafica;

    @Column(name = "email")
    private String email;

    @Column(name = "password", length = 45)
    private String password;

    @Column(name = "provider", length = 15)
    private String provider;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tpl_utente", nullable = false)
    private TplUtenteEntity tplUtente;

    @OneToMany(mappedBy = "utente")
    private List<AcquistoEntity> acquisti = new ArrayList<>();

    @OneToMany(mappedBy = "utente")
    private List<TokenEntity> tokens = new ArrayList<>();

}