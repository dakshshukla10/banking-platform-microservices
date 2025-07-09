package com.example.auth.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    /**
     * We set the UUID in UserServiceImpl before persisting,
     * so no generator annotation is needed here.
     */
    @Id
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false)
    private String password;

    /**
     * Default role ensures the column is never NULL and
     * avoids the “null value in column roles” DB error.
     */
    @Column(nullable = false)
    private String roles = "ROLE_USER";

    // ── Constructors ───────────────────────────────────────

    public User() { }

    public User(UUID id, String username, String password, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // ── Getters & Setters ─────────────────────────────────

    public UUID getId()            { return id; }
    public void setId(UUID id)     { this.id = id; }

    public String getUsername()    { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword()    { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRoles()       { return roles; }
    public void setRoles(String roles)      { this.roles = roles; }
}