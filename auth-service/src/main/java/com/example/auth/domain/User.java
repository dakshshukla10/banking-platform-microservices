package com.example.auth.domain;


import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name= "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String roles;

    //Getters and Setters

    public UUID getId() { return id;}
    public void setId(UUID id) { this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username) { this.username = username; }

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getRoles(){return roles;}
    public void setRoles(String roles){ this.roles = roles;}
}