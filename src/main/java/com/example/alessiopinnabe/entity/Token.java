package com.example.alessiopinnabe.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "token")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Token implements Serializable {
    @Id
    @Size(max = 36)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid" ,strategy = "uuid")
    @Column(name = "id", nullable = false , length = 36)
    private String id;

    @Lob
    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "token_type")
    private String tokenType;

    @Column(name = "expires_in_seconds")
    private Integer expiresInSeconds;

    @Lob
    @Column(name = "scope")
    private String scope;

    @Column(name = "date_creation")
    private Timestamp dateCreation;

    @Column(name = "date_exiration")
    private Timestamp dateExiration;

    @Column(name = "provider")
    private String provider;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

}