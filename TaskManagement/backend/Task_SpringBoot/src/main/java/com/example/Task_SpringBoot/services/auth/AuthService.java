package com.example.Task_SpringBoot.services.auth;

import com.example.Task_SpringBoot.dto.SignUpRequest;
import com.example.Task_SpringBoot.dto.UserDto;

public interface AuthService {

   UserDto signupUser(SignUpRequest signUpRequest);

   boolean hasUserWithEmail(String email);
}
