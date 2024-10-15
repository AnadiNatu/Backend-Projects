package com.example.admin_service.dtos;

import com.example.admin_service.enums.UserRole;
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
