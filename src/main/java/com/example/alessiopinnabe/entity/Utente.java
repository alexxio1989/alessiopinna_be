package com.example.alessiopinnabe.entity;

import javax.persistence.*;

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idutente", nullable = false)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "skypeID")
    private String skypeID;

    @Column(name = "email")
    private String email;

    @Column(name = "password", length = 45)
    private String password;

    @Column(name = "role", length = 45)
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSkypeID() {
        return skypeID;
    }

    public void setSkypeID(String skypeID) {
        this.skypeID = skypeID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}