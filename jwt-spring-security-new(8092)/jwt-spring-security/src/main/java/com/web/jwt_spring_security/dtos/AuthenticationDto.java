package com.web.jwt_spring_security.dtos;

import lombok.Data;

@Data
public class AuthenticationDto {

    private String email;

    private String password;
}
