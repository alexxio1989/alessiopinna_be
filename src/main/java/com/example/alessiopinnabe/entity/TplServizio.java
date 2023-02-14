package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tpl_servizio")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TplServizio {
    @Id
    @Size(max = 36)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid" ,strategy = "uuid")
    @Column(name = "id_tpl_servizio", nullable = false , length = 36)
    String id;

    @Column(name = "descrizione")
    private String descrizione;

    @Lob
    @Column(name = "desc_ext")
    private String descExt;

}