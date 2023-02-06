package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "acquisto_prodotto")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AttributeOverride(name = "id" , column = @Column(name = "id_acquisto_prodotto"))
public class AcquistoProdotto extends _Acquisto {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id",referencedColumnName = "id", nullable = false ,insertable = false ,updatable = false)
    private Prodotto prodotto;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "acquistoProdotto")
    private DetailAcquistoProdotto detailAcquistoProdotto;

}