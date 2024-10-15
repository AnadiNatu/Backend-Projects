package com.example.Task_SpringBoot.services.jwt;

import com.example.Task_SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService{


    @Autowired
    private UserRepository userRepository;

//    @Override
//    public UserDetailsService customUserDetailsService() {
//        return  new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                return userRepository.findFirstByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
//            }
//        };
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findFirstByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));
    }
}
