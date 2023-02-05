package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "detail_acquisto_prodotto")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DetailAcquistoProdotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "key_payment")
    private String keyPayment;

    @Column(name = "type", length = 100)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id",referencedColumnName = "id", nullable = false ,insertable = false ,updatable = false)
    private AcquistoProdotto acquistoProdotto;

}