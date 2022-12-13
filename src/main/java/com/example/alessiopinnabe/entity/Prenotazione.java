package com.example.alessiopinnabe.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prenotazione")
public class Prenotazione {
    @EmbeddedId
    private PrenotazioneId id;

    @MapsId("utenteIdutente")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_idutente", nullable = false)
    private Utente utenteIdutente;

    @MapsId("corsoIdcorso")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "corso_idcorso", nullable = false)
    private Corso corsoIdcorso;

    @Column(name = "qnt_ore")
    private String qntOre;

    @Column(name = "data_prenotazione")
    private LocalDate dataPrenotazione;

    @Column(name = "ora_inizio")
    private String oraInizio;

    public PrenotazioneId getId() {
        return id;
    }

    public void setId(PrenotazioneId id) {
        this.id = id;
    }

    public Utente getUtenteIdutente() {
        return utenteIdutente;
    }

    public void setUtenteIdutente(Utente utenteIdutente) {
        this.utenteIdutente = utenteIdutente;
    }

    public Corso getCorsoIdcorso() {
        return corsoIdcorso;
    }

    public void setCorsoIdcorso(Corso corsoIdcorso) {
        this.corsoIdcorso = corsoIdcorso;
    }

    public String getQntOre() {
        return qntOre;
    }

    public void setQntOre(String qntOre) {
        this.qntOre = qntOre;
    }

    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public String getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(String oraInizio) {
        this.oraInizio = oraInizio;
    }

}