package com.example.alessiopinnabe.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrenotazioneId implements Serializable {
    private static final long serialVersionUID = 7185426776927952403L;
    @Column(name = "utente_idutente", nullable = false)
    private Integer utenteIdutente;

    @Column(name = "corso_idcorso", nullable = false)
    private Integer corsoIdcorso;

    public Integer getUtenteIdutente() {
        return utenteIdutente;
    }

    public void setUtenteIdutente(Integer utenteIdutente) {
        this.utenteIdutente = utenteIdutente;
    }

    public Integer getCorsoIdcorso() {
        return corsoIdcorso;
    }

    public void setCorsoIdcorso(Integer corsoIdcorso) {
        this.corsoIdcorso = corsoIdcorso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PrenotazioneId entity = (PrenotazioneId) o;
        return Objects.equals(this.utenteIdutente, entity.utenteIdutente) &&
                Objects.equals(this.corsoIdcorso, entity.corsoIdcorso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utenteIdutente, corsoIdcorso);
    }

}