package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "img_servizio")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImgServizio implements Serializable {
    @Id
    @Size(max = 36)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid" ,strategy = "uuid")
    @Column(name = "id_image", nullable = false , length = 36)
    private String id;

    @Column(name = "`key`")
    private String key;

    @Column(name = "img_url", length = 45)
    private String imgUrl;

    @Column(name = "img")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_servizio",referencedColumnName = "id_servizio", nullable = false)
    private Servizio servizio;

}