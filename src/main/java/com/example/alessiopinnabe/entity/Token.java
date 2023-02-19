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
    String id;

    @Lob
    @Column(name = "access_token")
    String accessToken;

    @Column(name = "token_type")
    String tokenType;

    @Column(name = "expires_in_seconds")
    Integer expiresInSeconds;

    @Lob
    @Column(name = "scope")
    String scope;

    @Column(name = "date_creation")
    Timestamp dateCreation;

    @Column(name = "date_exiration")
    Timestamp dateExiration;

    @Column(name = "provider")
    String provider;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_utente", nullable = false)
    Utente utente;

}