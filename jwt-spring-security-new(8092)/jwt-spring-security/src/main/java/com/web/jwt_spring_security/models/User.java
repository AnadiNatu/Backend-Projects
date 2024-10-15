package com.web.jwt_spring_security.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_table")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email ;

    private String password;

    private String phone;

}
