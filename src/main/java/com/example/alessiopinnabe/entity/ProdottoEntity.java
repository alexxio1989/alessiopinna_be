package com.example.alessiopinnabe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "prodotto")
@Getter
@Setter
public class ProdottoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prodotto", nullable = false)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Lob
    @Column(name = "nome_ext")
    private String nomeExt;

    @Lob
    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "img_primaria")
    private byte[] imgPrimaria;

    @Column(name = "data_creazione")
    private Date dataCreazione;

    @Column(name = "enable")
    private Integer enable;

    @Column(name = "prezzo", precision = 9, scale = 2)
    private BigDecimal prezzo;

    @Column(name = "quantita")
    private Integer quantita;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tpl_prodotto", nullable = false)
    private TplProdottoEntity tplProdotto;

}