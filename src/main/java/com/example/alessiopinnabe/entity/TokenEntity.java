package com.example.alessiopinnabe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "token")
@Getter
@Setter
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "token_type")
    private String tokenType;

    @Column(name = "expires_in_seconds")
    private Integer expiresInSeconds;

    @Lob
    @Column(name = "scope")
    private String scope;

    @Column(name = "date_creation")
    private Timestamp dateCreation;

    @Column(name = "date_exiration")
    private Timestamp dateExiration;

    @Column(name = "provider")
    private String provider;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_utente", nullable = false)
    private UtenteEntity utente;

}