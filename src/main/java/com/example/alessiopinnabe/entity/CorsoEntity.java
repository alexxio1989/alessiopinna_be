package com.example.alessiopinnabe.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "corso")
public class CorsoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcorso", nullable = false)
    private Integer id;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "titolo_ext")
    private String titoloExt;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tpl_corso_idtpl_corso", nullable = false)
    private TplCorsoEntity tplCorsoIdtplCorso;

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

    public TplCorsoEntity getTplCorsoIdtplCorso() {
        return tplCorsoIdtplCorso;
    }

    public void setTplCorsoIdtplCorso(TplCorsoEntity tplCorsoIdtplCorso) {
        this.tplCorsoIdtplCorso = tplCorsoIdtplCorso;
    }

    public String getTitoloExt() {
        return titoloExt;
    }

    public void setTitoloExt(String titoloExt) {
        this.titoloExt = titoloExt;
    }
}