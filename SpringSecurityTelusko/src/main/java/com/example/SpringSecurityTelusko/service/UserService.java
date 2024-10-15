package com.example.SpringSecurityTelusko.service;

import com.example.SpringSecurityTelusko.model.Users;
import com.example.SpringSecurityTelusko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepository repo;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

    public String verify(Users user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }

























//    @Autowired
//    private UserRepository repository;
//
//    @Autowired
//    private JWTService jwtService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); //  We can also make a bean of the password encoder and injecting(autowire) it here
//    public Users register(Users user){
//
//        user.setPassword(encoder.encode(user.getPassword()));
//        return repository.save(user);
//    }
//
//    public String verify(Users user) {
//// (12)
//        // Login Controller -> UserService -> Authentication Manager -> Get authenticated Authentication obj -> Gives username to JWT service to generate a token
//
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())); // what this line of code doe is that it converts an unauthorized Authentication object to an authorized Authentication object so that we can convert it into jwt
//// UsernamePasswordAuthenticationToken creates a unauthenticated Authentication token . unauthenticated Authentication object -> Authentication Provider -> authenticated Authentication object
//
//        if (authentication.isAuthenticated()){
//            return jwtService.generateToken(user.getUsername());  // Basically behind the seens the verification processes for the user is happening , it is just that we are taking control of the login
//        }
//        return "fail";
//    }

}
