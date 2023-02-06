package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "detail_acquisto_prodotto")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DetailAcquistoProdotto {
    @Id
    @Size(max = 36)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid" ,strategy = "uuid")
    @Column(name = "id", nullable = false , length = 36)
    private Integer id;

    @Column(name = "key_payment")
    private String keyPayment;

    @Column(name = "type", length = 100)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_acquisto_prodotto",referencedColumnName = "id_acquisto_prodotto", nullable = false ,insertable = false ,updatable = false)
    private AcquistoProdotto acquistoProdotto;

}