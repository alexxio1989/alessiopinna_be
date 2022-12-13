package com.example.alessiopinnabe.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "corso")
public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcorso", nullable = false)
    private Integer id;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "tipo")
    private String tipo;

    @Lob
    @Column(name = "descrizione")
    private String descrizione;

    @Lob
    @Column(name = "giorni_orari")
    private String giorniOrari;

    @Column(name = "img_name")
    private String imgName;

    @Column(name = "data_creazione")
    private Date dataCreazione;

    @Column(name = "enable")
    private Integer enable;

    @Column(name = "prezzo", precision = 9, scale = 2)
    private BigDecimal prezzo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getGiorniOrari() {
        return giorniOrari;
    }

    public void setGiorniOrari(String giorniOrari) {
        this.giorniOrari = giorniOrari;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
}