package com.example.SpringSecurityTelusko.service;

import com.example.SpringSecurityTelusko.model.UserPrinciple;
import com.example.SpringSecurityTelusko.model.Users;
import com.example.SpringSecurityTelusko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrinciple(user);
    }












//    @Autowired
//    private UserRepository repository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Users user = repository.findByUsername(username);
//
//        if (user == null){
//            System.out.println("User not found");
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        return new UserPrinciple(user); // we are sending the user to custom UserDetailsService to set the fields so that we can manipulate it later
//    }
}
