package com.web.pokemon_review_new.controller;

import com.web.pokemon_review_new.dto.AuthResponseDto;
import com.web.pokemon_review_new.dto.LoginDto;
import com.web.pokemon_review_new.dto.RegisterDto;
import com.web.pokemon_review_new.model.Role;
import com.web.pokemon_review_new.model.UserEntity;
import com.web.pokemon_review_new.repository.RoleRepository;
import com.web.pokemon_review_new.repository.UserRepository;
import com.web.pokemon_review_new.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JWTGenerator jwtGenerator;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){

        if (userRepository.existsByUsername(registerDto.getUsername()))
        {
            return new ResponseEntity<>("Username is taken " , HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();

        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role role = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        return new ResponseEntity<>("User Register Successfully" , HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<>(new AuthResponseDto(token) , HttpStatus.OK);

        //SecurityContextHolder ensure that once we login we don't need to keep relogin in when trying to access endpoints of the same url

    }
}
