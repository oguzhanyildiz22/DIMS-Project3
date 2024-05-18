package com.sau.dims.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 16)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 16)
    private String name;

    @Column(length = 16)
    private String surname;

    @Column(nullable = false, length = 16)
    private String role;

    @Column(name = "is_locked", columnDefinition = "boolean default false")
    private boolean isLocked;
}
