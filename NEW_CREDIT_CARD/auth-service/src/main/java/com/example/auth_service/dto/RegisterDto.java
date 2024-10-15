package com.example.auth_service.dto;

import lombok.Data;

@Data
public class RegisterDto {



    private String fname;
    private String lname;
    private String phoneNumber;
    private String email;
    private String password;
    private String age;
}
