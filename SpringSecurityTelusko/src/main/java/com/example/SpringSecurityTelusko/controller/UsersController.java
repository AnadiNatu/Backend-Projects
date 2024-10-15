package com.example.SpringSecurityTelusko.controller;

import com.example.SpringSecurityTelusko.model.Users;
import com.example.SpringSecurityTelusko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody  Users user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return userService.verify(user);
    }
}
