package com.web.jwt_spring_security.services;

import com.web.jwt_spring_security.dtos.SignupDto;
import com.web.jwt_spring_security.dtos.UserDto;

public interface AuthService {
    UserDto createUser(SignupDto signupDto);
}
