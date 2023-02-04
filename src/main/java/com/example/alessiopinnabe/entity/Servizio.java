package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Lob
    @Column(name = "nome_ext")
    private String nomeExt;

    @Lob
    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "data_creazione")
    private Date dataCreazione;

    @Column(name = "enable")
    private Integer enable;

    @Column(name = "prezzo", precision = 9, scale = 2)
    private BigDecimal prezzo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_servizio", nullable = false)
    private TplServizio tipoServizio;

    @OneToMany(mappedBy = "servizio")
    private List<ImgServizio> images = new ArrayList<>();

}