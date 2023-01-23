package com.example.alessiopinnabe.entity;

import javax.persistence.*;

@Entity
@Table(name = "tpl_prodotto")
public class TplProdottoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tpl_prodotto", nullable = false)
    private Integer id;

    @Column(name = "codice", length = 100)
    private String codice;

    @Column(name = "descrizione")
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