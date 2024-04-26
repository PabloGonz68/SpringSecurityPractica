package com.pepe.primeraweb.com.pepe.primerawebspring.entities;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private boolean enabled;
    private String role;
}
