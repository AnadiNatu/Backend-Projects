package com.example.NewSecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // this annotation tells spring boot that we also implemented authentication n the methods of the controller
public class SecurityConfig {


    @Bean
    //Authentication
    public UserDetailsService userDetailsService(){
       //(1)
        // It is the core interface in Spring Security , used to retrieve user-related data .
        // It primary purpose to fetch details of user frm DB(username,password and role)
        // We want our UserDetailService class to interact with the database

//        UserDetails admin = User.withUserDetails("Anadi")
//                .password(encoder.encode("Pwd1"))
//                .roles("ADMIN").build();
// Over here we are building a UserDetails object manually
//        UserDetails user = User.withUserDetails("John")
//                .password(encoder.encode("Pwd2"))
//                .roles("ADMIN" ,"USER","HR").build();
//
//
//        return new UserInfo
        return new UserInfoUserDetailService(); // We want our own UserDetailsService that fetched user details from our DB
    }


    @Bean
    //Authorization
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    //(3)
        // It determines how request to you application are secured. Security Filter Chain object , which is a collection of filters that are applied to incoming HTTP security
        //HttpSecurity parameter allows to customize security configurations , like specifying URLs should be accessible without authentication,enforcing Https and more
        // This method is essential because it ets up the security rules that every incoming Http request must pass through . Without it the application wouldn't know how to handle security for different requests , leaving it vulnerable

        return http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/products/welcome" , "/products/new").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/products/**")
                .authenticated()
                .and()
                .formLogin()
                .and()
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }



    @Bean
    public AuthenticationProvider authenticationProvider(){
    //(6)
        // What this AuthenticationProvider does is that it  creates a UserDetail object
        //  It is responsible for validation user credentials(username and password)
        // working - when a user tries to log in , Spring Security passes the authentication request(like a username and password) to the AuthenticationProvider
        // AuthenticationProvider allows UserDetailsService to load the user details from database
        // main method in AuthenticationProvider is authenticate(), which takes an Authentication object (containing credentials) and returns a fully authenticated object if successful.




        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //(7)
        // Its a specific implementation of AuthenticationProvider . It provides validations for user credential against tored data , typically fetched from a database via a UserDetailsService
        // DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // When a user attempts to log in, DaoAuthenticationProvider retrieves the user details, checks the password, and if valid, considers the user authenticated.

        authenticationProvider.setUserDetailsService(userDetailsService());

        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }


    // We have to create all the beans manually for the implementation of web security
    // The con of this method of implementing this type of security is that it we to provide for username and password for every api we hit even after logging in
}


