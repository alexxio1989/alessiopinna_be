package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "servizio")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
public class Servizio {
    @Id
    @Size(max = 36)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid" ,strategy = "uuid")
    @Column(name = "id", nullable = false , length = 36)
    String id;

    @Column(name = "nome")
    String nome;

    @Column(name = "nome_ext")
    String nomeExt;

    @Column(name = "descrizione")
    String descrizione;

    @Column(name = "data_creazione")
    Date dataCreazione;

    @Column(name = "enable")
    Integer enable;

    @Column(name = "prezzo", precision = 9, scale = 2)
    BigDecimal prezzo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_servizio", nullable = false)
    TplServizio tipoServizio;

    @OneToMany(mappedBy = "servizio")
    List<ImgServizio> images = new ArrayList<>();

}