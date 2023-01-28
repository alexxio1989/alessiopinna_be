package com.example.alessiopinnabe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "detail_acquisto")
@Getter
@Setter
public class DetailAcquistoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail_acquisto", nullable = false)
    private Integer id;

    @Column(name = "key_payment")
    private String keyPayment;

    @Column(name = "type", length = 100)
    private String type;

}