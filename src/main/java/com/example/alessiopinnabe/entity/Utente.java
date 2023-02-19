package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "utente")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Utente implements Serializable {
    @Id
    @Size(max = 36)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid" ,strategy = "uuid")
    @Column(name = "id_utente", nullable = false , length = 36)
    String id;

    @Column(name = "anagrafica", length = 100)
    String anagrafica;

    @Column(name = "email")
    String email;

    @Column(name = "password", length = 45)
    String password;

    @Column(name = "provider", length = 15)
    String provider;

    @Lob
    @Column(name = "photo_url")
    String photoUrl;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tipo_utente", nullable = false)
    TplUtente tipoUtente;

    @OneToMany(mappedBy = "utente")
    List<AcquistoProdotto> prodottiAcquistati = new ArrayList<>();

    @OneToMany(mappedBy = "utente")
    List<AcquistoEvento> eventiAcquistati = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "utente")
    List<Token> tokens = new ArrayList<>();

}