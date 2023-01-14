package com.example.alessiopinnabe.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_token")
public class UserTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser_token", nullable = false)
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

    @Column(name = "provider")
    private String provider;

    @Column(name = "date_creation")
    private Timestamp dateCreation;

    @Column(name = "date_exiration")
    private Timestamp dateExiration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_idutente", nullable = false)
    private UtenteEntity utenteIdutente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpiresInSeconds() {
        return expiresInSeconds;
    }

    public void setExpiresInSeconds(Integer expiresInSeconds) {
        this.expiresInSeconds = expiresInSeconds;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Timestamp getDateExiration() {
        return dateExiration;
    }

    public void setDateExiration(Timestamp dateExiration) {
        this.dateExiration = dateExiration;
    }

    public UtenteEntity getUtenteIdutente() {
        return utenteIdutente;
    }

    public void setUtenteIdutente(UtenteEntity utenteIdutente) {
        this.utenteIdutente = utenteIdutente;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}