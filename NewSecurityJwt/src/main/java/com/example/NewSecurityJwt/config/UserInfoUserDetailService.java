package com.example.NewSecurityJwt.config;


import com.example.NewSecurityJwt.entity.UserInfo;
import com.example.NewSecurityJwt.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailService  implements UserDetailsService
{
    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = repository.findByUsername(username);

        if (userInfo == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserInfoUserDetails(userInfo);
    }
}


//{
//
//
//    @Autowired
//    private UserInfoRepository repository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<UserInfo> userInfo = repository.findByUsername(username);
//
//        return userInfo.map(UserInfoUserDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found" + username));
//
//    }
//
//
//}
