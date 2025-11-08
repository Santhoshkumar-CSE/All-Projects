package com.authService.june.authServiceJune.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="app_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name = "username",nullable = false,unique = true)
    private String userName;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name ="password",nullable = false,unique = true)
    private String password;

    @Column(name="role")
    private String role;
}
