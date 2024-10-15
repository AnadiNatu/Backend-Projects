package com.anadi.ServiceBookingSystem.controller;


import com.anadi.ServiceBookingSystem.dto.AuthenticationRequest;
import com.anadi.ServiceBookingSystem.dto.SignupRequestDto;
import com.anadi.ServiceBookingSystem.dto.UserDto;
import com.anadi.ServiceBookingSystem.entity.User;
import com.anadi.ServiceBookingSystem.repository.UserRepository;
import com.anadi.ServiceBookingSystem.services.authentication.AuthService;
import com.anadi.ServiceBookingSystem.services.jwt.UserDetailServiceImpl;
import com.anadi.ServiceBookingSystem.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController

public class AuthenticationController {


    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public static final String TOKEN_PREFIX = "Bearer";

    public static final String HEADER_PREFIX = "Authorization";

    @PostMapping("/client/sigh-up")
    public ResponseEntity<?> signUpClient(@RequestBody SignupRequestDto signupRequestDto){

        if (authService.presentByEmail(signupRequestDto.getEmail())) {
            return new ResponseEntity<>("Client already exists with this Email" , HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto createdUser = authService.signupClient(signupRequestDto);

        return new ResponseEntity<>(createdUser  , HttpStatus.CREATED);
    }

    @PostMapping("/company/sigh-up")
    public ResponseEntity<?> signUpCompany(@RequestBody SignupRequestDto signupRequestDto){

        if (authService.presentByEmail(signupRequestDto.getEmail())) {
            return new ResponseEntity<>("Client already exists with this Email" , HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto createdUser = authService.signupCompany(signupRequestDto);

        return new ResponseEntity<>(createdUser  , HttpStatus.CREATED);
    }

    @PostMapping("/autheticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest , HttpServletResponse response) throws IOException, JSONException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername()
                    ,authenticationRequest.getPassword()));

        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Inappropriate username or password" , e);
        }

        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        User user = userRepository.findFirstByEmail(authenticationRequest.getUsername());

        response.getWriter().write(new JSONObject()
                .put("userId", user.getId())
                .put("role" , user.getRole())
                .toString());

        response.addHeader("Access-Control-Expose-Header" , "Authenticate");
        response.addHeader("Access_Controll-Allow-Header" , "Authorization" +
                " X-PINGOTHER , Origin , X-Requested-With , Content-Type , Accept , X-Custom-header");
        //this part of code is help expose the headers to our angular project

        response.addHeader(HEADER_PREFIX,TOKEN_PREFIX+jwt);
    }
}
