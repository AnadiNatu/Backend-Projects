package com.example.auth_service.dto;

import com.example.auth_service.enums.UserRole;
import lombok.Data;

@Data
public class CustomerDto {

    private Long id;
    private String fname;
    private String lname;
    private String phoneNumber;
    private String email;
    private String age;
    private UserRole userRole;
}
