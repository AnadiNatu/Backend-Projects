package com.pokemon.api.security.security1;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("blah")
                .password("Anadi")
                .roles("USERS")
                .and()
                .withUser("blah2")
                .password("Anadi2")
                .roles("ADMIN");

        //what AuthenticationManager does is that it overrides the default SpringSecurity with our custom authentication credentials
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USERS" , "ADMIN")
                .antMatchers("/").permitAll()
                .and()
                .formLogin();

                // we also have .anyRole() method to authorize user with any Role to access the path specified
                // .permitAll()
                // .hasAnyRole() - what this method does that it specifies a list of roles that have authorization to do that tasks/hit that endpoints

        }
}
