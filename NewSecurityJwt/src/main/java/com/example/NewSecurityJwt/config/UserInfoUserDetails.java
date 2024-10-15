package com.example.NewSecurityJwt.config;

import com.example.NewSecurityJwt.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


public class UserInfoUserDetails implements UserDetails
{

    private final UserInfo user;

    public UserInfoUserDetails(UserInfo userInfo) {

        this.user = userInfo;

//        username=userInfo.getUsername();
//        password=userInfo.getPassword();
//        authorities= Arrays.stream(userInfo.getRoles().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}





















//{
//
//    // The purpose of this class is that we need to convert the userInfo coming from our DB to UserDetails
//
//    private String name;
//    private String password;
//    private List<GrantedAuthority> authorities;
//
//
//    public UserInfoUserDetails(UserInfo userInfo){
//
//        name = userInfo.getName();
//        password = userInfo.getPassword();
//
//        authorities = Arrays.stream(userInfo.getRoles().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//    // we are mapping the userInfo to our UserDetail fields so that it can carry out authentication and authorization
//    // this is something we have to do in all our securities implementation , we map our custom user field to appropriate UserDetails fields
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return name;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
