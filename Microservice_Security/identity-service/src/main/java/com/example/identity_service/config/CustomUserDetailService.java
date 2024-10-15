package com.example.identity_service.config;

import com.example.identity_service.entity.UserCredential;
import com.example.identity_service.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Optional<UserCredential> credential = repository.findByName(username);


       return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("username not found"+ username));


    }
}
