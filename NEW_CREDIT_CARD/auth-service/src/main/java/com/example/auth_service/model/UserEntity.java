package com.example.auth_service.model;

import jakarta.persistence.Entity;

@Entity
public class UserEntity {

    private int id;
    private String name;
    private String email;
    private String password;

}
