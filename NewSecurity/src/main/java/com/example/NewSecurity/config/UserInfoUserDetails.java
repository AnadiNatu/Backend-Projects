package com.example.NewSecurity.config;

import com.example.NewSecurity.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {
//(4) - Flows comes from loadByUsername() method after custom UserDetailsService is invoked
    // The purpose of this class is that we need to convert the userInfo coming from our DB to UserDetails
    // UserDetails is an interface in Spring Security that represents the core user information
    // The primary purpose of UerDetails is to ENCAPSULATE  a user's information , such as username,password and roles , in a way that Spring Security can use during the authentication process
    // Once authenticated , UserDetails object is stored in the SecurityContext , which allows the application to access the user's information and roles throughout the session

    private String name;
    private String password;
    private List<GrantedAuthority> authorities;


    public UserInfoUserDetails(UserInfo userInfo){

        name = userInfo.getName();
        password = userInfo.getPassword();

        authorities = Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new) //making an object of SimpleGrantedAuthorities object
                .collect(Collectors.toList());
    }
// (5)
    // we are mapping the userInfo to our UserDetail fields so that it can carry out authentication and authorization
    // this is something we have to do in all our securities implementation , we map our custom user field to appropriate UserDetails fields



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
