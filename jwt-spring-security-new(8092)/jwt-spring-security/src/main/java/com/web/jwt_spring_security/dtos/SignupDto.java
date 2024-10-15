package com.web.jwt_spring_security.dtos;

import lombok.Data;

@Data
public class SignupDto {

    private String name;

    private String email;

    private String password;

}
