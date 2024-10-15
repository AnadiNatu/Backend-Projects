package com.example.ServiceBookingImpl.controller;

import com.example.ServiceBookingImpl.dto.*;
import com.example.ServiceBookingImpl.entity.Users;
import com.example.ServiceBookingImpl.repository.UsersRepository;
import com.example.ServiceBookingImpl.security.JwtUtils;
import com.example.ServiceBookingImpl.security.UserDetailsServiceImpl;
import com.example.ServiceBookingImpl.services.auth.AuthService;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {


    @Autowired
    private AuthService authService;

//    @Autowired
//    private UsersRepository usersRepository;



    @PostMapping("/signup/company")
    public ResponseEntity<?> signupCompany(@RequestBody CompanySignUpDTO signUpDTO){

        if (authService.hasUserWithUsername(signUpDTO.getUsername())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Company with same user name exists");
        }


        UserDTO userDTO = authService.signupCompany(signUpDTO);

        if (userDTO  == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company not created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);

    }



    @PostMapping("/signup/client")
    public ResponseEntity<?> signupClient(@RequestBody SignUpDTO signUpDTO){

        if (authService.hasUserWithUsername(signUpDTO.getUsername())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Client with same username exists");
        }

        UserDTO userDTO = authService.signupClient(signUpDTO);

        if (userDTO == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client not created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }



    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername() , authenticationRequest.getPassword()));
        }catch (BadCredentialsException ex){
            throw new BadCredentialsException("Incorrect username or password");
        }


        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        Optional<Users> optionalUsers = usersRepository.findByUsername(authenticationRequest.getUsername());

        String jwtToken = jwtUtils.generateToken(optionalUsers.get().getUsername());

        AuthenticationResponse authResponse = new AuthenticationResponse();
        if (optionalUsers.isPresent()){
            authResponse.setJwt(jwtToken);
            authResponse.setUserId(optionalUsers.get().getId());
            authResponse.setUserRole(optionalUsers.get().getRole());
        }

        return authResponse;
    }





}
