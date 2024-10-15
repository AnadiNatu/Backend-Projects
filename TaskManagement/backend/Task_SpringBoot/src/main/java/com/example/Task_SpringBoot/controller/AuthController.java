package com.example.Task_SpringBoot.controller;

import com.example.Task_SpringBoot.dto.AuthenticationRequest;
import com.example.Task_SpringBoot.dto.AuthenticationResponse;
import com.example.Task_SpringBoot.dto.SignUpRequest;
import com.example.Task_SpringBoot.dto.UserDto;
import com.example.Task_SpringBoot.entities.Users;
import com.example.Task_SpringBoot.repository.UserRepository;
import com.example.Task_SpringBoot.services.auth.AuthService;
import com.example.Task_SpringBoot.services.jwt.UserServiceImpl;
import com.example.Task_SpringBoot.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignUpRequest signUpRequest){

        if (authService.hasUserWithEmail(signUpRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exists with this email " + signUpRequest);
        }

        UserDto userDto = authService.signupUser(signUpRequest);

        if (userDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not Created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);

    }


    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest){
    try{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()));
    }catch (BadCredentialsException ex){
        throw new BadCredentialsException("Incorrect username or ");
    }
    UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
    Optional<Users> optionalUser = userRepository.findFirstByEmail(authenticationRequest.getEmail());
    String jwtToken = jwtUtil.generateToken(optionalUser.get().getEmail());
    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    if (optionalUser.isPresent()) {
        authenticationResponse.setJwt(jwtToken);
        authenticationResponse.setUserId(optionalUser.get().getId());
        authenticationResponse.setUserRole(optionalUser.get().getUserRole());
    }

    return authenticationResponse;
    }
}
