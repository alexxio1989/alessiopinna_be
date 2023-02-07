package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "acquisto")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
public class Acquisto implements Serializable {
    @Id
    @Size(max = 36)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid" ,strategy = "uuid")
    @Column(name = "id_acquisto", nullable = false , length = 36)
    private String id;

    @Column(name = "quantita", nullable = false)
    private Integer quantita;

    @Column(name = "data_acquisto", nullable = false)
    private Timestamp dataAcquisto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_utente",referencedColumnName = "id_utente", nullable = false ,insertable = false ,updatable = false)
    private Utente utente;

}
