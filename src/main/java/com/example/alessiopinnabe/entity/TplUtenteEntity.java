package com.example.alessiopinnabe.entity;

import javax.persistence.*;

@Entity
@Table(name = "tpl_utente")
public class TplUtenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtpl_utente", nullable = false)
    private Integer id;

    @Column(name = "codice", length = 45)
    private String codice;

    @Column(name = "descrizione", length = 45)
    private String descrizione;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

}