package com.example.alessiopinnabe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utente")
@Getter
@Setter
public class UtenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utente", nullable = false)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password", length = 45)
    private String password;

    @Column(name = "provider", length = 15)
    private String provider;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tpl_utente", nullable = false)
    private TplUtenteEntity tplUtente;

    @OneToMany(mappedBy = "utente")
    private List<TokenEntity> tokens = new ArrayList<>();
    
}