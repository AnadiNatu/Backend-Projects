package com.example.springbootsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    DataSource dataSource;

    //Spring Boot has autoconfigured the datasource by looking into the pom file , as in with datasource it has and uses that datasource as that

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username , password , enabled " +
                        " from users " +
                        "where username = ?")
                .authoritiesByUsernameQuery("select username , authority "+
                                             " from authorities " +
                                              " where username = ?");

//last two methods what they do is that they tell spring security what queries to run to get roles and authentication
        // this line basically points our spring security towards our data
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .and().formLogin();

    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){

        return NoOpPasswordEncoder.getInstance();
    }


}
