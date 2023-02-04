package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "acquisto_prodotto")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AcquistoProdotto extends _Acquisto {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Prodotto prodotto;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "acquistoProdotto")
    private DetailAcquistoProdotto detailAcquistoProdotto;

}