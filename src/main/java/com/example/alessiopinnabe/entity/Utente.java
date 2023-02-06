package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "utente")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Utente {
    @Id
    @Size(max = 36)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid" ,strategy = "uuid")
    @Column(name = "id_utente", nullable = false , length = 36)
    private String id;

    @Column(name = "anagrafica", length = 100)
    private String anagrafica;

    @Column(name = "email")
    private String email;

    @Column(name = "password", length = 45)
    private String password;

    @Column(name = "provider", length = 15)
    private String provider;

    @Lob
    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_utente", nullable = false)
    private TplUtente tipoUtente;

    @OneToMany(mappedBy = "utente")
    private List<AcquistoProdotto> prodottiAcquistati = new ArrayList<>();

    @OneToMany(mappedBy = "utente")
    private List<AcquistoEvento> eventiAcquistati = new ArrayList<>();

    @OneToMany(mappedBy = "utente")
    private List<Token> tokens = new ArrayList<>();

}