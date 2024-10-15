package com.example.ServiceBookingImpl.dto;

import com.example.ServiceBookingImpl.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {


    private String jwt;
    private Long userId;
    private UserRole userRole;


}
