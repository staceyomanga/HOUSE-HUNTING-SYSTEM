package com.hostelfinder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    private String passwordHash;
    private String phone;
    private String role;
}
