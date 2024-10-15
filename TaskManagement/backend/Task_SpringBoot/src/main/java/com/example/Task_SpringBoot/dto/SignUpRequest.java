package com.example.Task_SpringBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class SignUpRequest {

    private String name;
    private String email;
    private String password;
}
