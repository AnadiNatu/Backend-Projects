package com.example.identity_service.controller;

import com.example.identity_service.dto.AuthRequest;
import com.example.identity_service.entity.UserCredential;
import com.example.identity_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/registery")
    public String addUser(@RequestBody UserCredential userCredential){

        return service.saveUser(userCredential);

    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest){

     Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername() , authRequest.getPassword()));

        if (authentication.isAuthenticated()){
            return service.generateToken(authRequest.getUsername());
        }else {
            throw new RuntimeException("Invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){

         service.validToken(token);

         return "Token is Valid";

    }


}
