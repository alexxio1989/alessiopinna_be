package com.example.alessiopinnabe.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "prenotazione")
public class PrenotazioneEntity {
    @EmbeddedId
    private PrenotazioneIdEntity id;

    @MapsId("utenteIdutente")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_idutente", nullable = false)
    private UtenteEntity utenteIdutente;

    @MapsId("corsoIdcorso")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "corso_idcorso", nullable = false)
    private CorsoEntity corsoIdcorso;

    @Column(name = "qnt_ore")
    private BigDecimal qntOre;

    @Column(name = "data_prenotazione")
    private LocalDate dataPrenotazione;

    @Column(name = "ora_inizio")
    private String oraInizio;

    public PrenotazioneIdEntity getId() {
        return id;
    }

    public void setId(PrenotazioneIdEntity id) {
        this.id = id;
    }

    public UtenteEntity getUtenteIdutente() {
        return utenteIdutente;
    }

    public void setUtenteIdutente(UtenteEntity utenteIdutente) {
        this.utenteIdutente = utenteIdutente;
    }

    public CorsoEntity getCorsoIdcorso() {
        return corsoIdcorso;
    }

    public void setCorsoIdcorso(CorsoEntity corsoIdcorso) {
        this.corsoIdcorso = corsoIdcorso;
    }

    public BigDecimal getQntOre() {
        return qntOre;
    }

    public void setQntOre(BigDecimal qntOre) {
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