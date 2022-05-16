package com.example.Banking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name="password")
    private String password;
    @Column(name="enabled")
    private Boolean enabled;
    @Column(name = "email")
    private String email;
    @Column(name="balance")
    private float balance;
    @Column(name="authority")
    private String authority;
}
