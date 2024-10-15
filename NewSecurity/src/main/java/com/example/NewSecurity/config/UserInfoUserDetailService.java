package com.example.NewSecurity.config;


import com.example.NewSecurity.entity.UserInfo;
import com.example.NewSecurity.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailService  implements UserDetailsService {


    @Autowired
    private UserInfoRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// (2)
        // Interface has only one method 'loadUserByUsername(Sting username)' . This method takes the username provided by the user during login and returns the corresponding "UserDetails" object
        // UserDetailsService , typically create a custom implementation of UserDetailsService
        // Security Flow - UserDetailsService is invoked after the authentication request is made but before the actual authentication process happens

        Optional<UserInfo> userInfo = repository.findByName(username);

        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found" + username));

    }


}
