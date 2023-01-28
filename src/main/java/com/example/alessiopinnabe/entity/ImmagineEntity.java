package com.example.alessiopinnabe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "immagine")
@Getter
@Setter
public class ImmagineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_immagine", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_prodotto", nullable = false)
    private ProdottoEntity prodotto;

    @Column(name = "`key`")
    private String key;

    @Column(name = "img_url", length = 45)
    private String imgUrl;

    @Column(name = "img")
    private byte[] img;

}