package com.example.alessiopinnabe.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utente")
public class UtenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idutente", nullable = false)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "skypeID")
    private String skypeID;

    @Column(name = "email")
    private String email;

    @Column(name = "password", length = 45)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tpl_utente_idtpl_utente", nullable = false)
    private TplUtenteEntity tplUtenteIdtplUtente;

    @Column(name = "provider", length = 15)
    private String provider;

    @OneToMany(mappedBy = "utenteIdutente")
    private List<UserTokenEntity> userTokens = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSkypeID() {
        return skypeID;
    }

    public void setSkypeID(String skypeID) {
        this.skypeID = skypeID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TplUtenteEntity getTplUtenteIdtplUtente() {
        return tplUtenteIdtplUtente;
    }

    public void setTplUtenteIdtplUtente(TplUtenteEntity tplUtenteIdtplUtente) {
        this.tplUtenteIdtplUtente = tplUtenteIdtplUtente;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public List<UserTokenEntity> getUserTokens() {
        return userTokens;
    }

    public void setUserTokens(List<UserTokenEntity> userTokens) {
        this.userTokens = userTokens;
    }

}