package com.anadi.ServiceBookingSystem.dto;

import com.anadi.ServiceBookingSystem.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String password;

    private String fname;

    private String lname;

    private String phone;

    private String email;

    private UserRole role;



    }


