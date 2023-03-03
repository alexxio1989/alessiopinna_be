package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "img_servizio")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImgServizio {
    @Id
    @Column(name = "id_image", nullable = false, length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_servizio", nullable = false)
    private Servizio servizio;

    @Lob
    @Column(name = "base64")
    private String base64;

    @Column(name = "url", length = 45)
    private String url;

}