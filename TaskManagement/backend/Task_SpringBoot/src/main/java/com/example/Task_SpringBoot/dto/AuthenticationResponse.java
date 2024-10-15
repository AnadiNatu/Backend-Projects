package com.example.Task_SpringBoot.dto;

import com.example.Task_SpringBoot.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String jwt;
    private Long userId;
    private UserRole userRole;

}
