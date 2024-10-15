package com.web.jwt_spring_security.controller;

import com.web.jwt_spring_security.dtos.SignupDto;
import com.web.jwt_spring_security.dtos.UserDto;
import com.web.jwt_spring_security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupUserController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<?> signupUser(@RequestBody SignupDto signupDTO) {
        UserDto createdUser = authService.createUser(signupDTO);
        if (createdUser == null){
            return new ResponseEntity<>("User not created, come again later!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
