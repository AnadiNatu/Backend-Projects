package com.example.identity_service.service;


import com.example.identity_service.entity.UserCredential;
import com.example.identity_service.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    public String saveUser(UserCredential credential){

        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);

        return "User added to the system";
    }


    public String generateToken(String username){

        return jwtService.generateToken(username);

    }

    public void validToken(String token){
        jwtService.validateToken(token);
    }
}


