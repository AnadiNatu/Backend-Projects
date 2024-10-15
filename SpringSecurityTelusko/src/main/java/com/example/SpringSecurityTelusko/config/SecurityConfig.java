package com.example.SpringSecurityTelusko.config;

import com.example.SpringSecurityTelusko.service.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration // Spring knows that this class is for the configurations
@EnableWebSecurity// this annotation basically says that dont go for the predefined/inbuilt configuration come here for the configurations
public class SecurityConfig {

    @Autowired
    private JWTFilter jwtFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(customizer -> customizer.disable()).
                authorizeHttpRequests(request -> request
                        .requestMatchers("login", "register").permitAll()
                        .anyRequest().authenticated()).
                httpBasic(Customizer.withDefaults()).
                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);


        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }























//    @Autowired
//    private MyUserDetailService userDetailsService;
//
//    @Autowired
//    private JWTFilter jwtFilter;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//      return   http.csrf(customizer -> customizer.disable())
//        .authorizeHttpRequests(request -> request.requestMatchers("register","login").permitAll().anyRequest().authenticated())
//              .httpBasic(Customizer.withDefaults())
//              .formLogin(Customizer.withDefaults())
//              .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//              .authenticationProvider(authenticationProvider())
//              .addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class)
//              .build();
//
//
//        // .formLogin(Customizer.withDefaults()) formLogin() gives us that basic form login page before we can access the endpoints.httpBasic(Customizer.withDefaults())
//        // to access the urls from postman we require httpBasic().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        // addFilterBefore()  - What it does is that it put our custom made filter before UPAF
//
//
//        // every method is from an interface . So we basically have to implement interface methods and pass HttpSecurity object
//
//         // build method returns the object of SecurityFilterChain
//    }
//
//
//    // Providing our own authentication provider
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//
////        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); //
//
//        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//
//        provider.setUserDetailsService(userDetailsService); // we will even provide our own user Detail service custom logic
//
//        return provider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//     return configuration.getAuthenticationManager();
//    }
//
//
//
////    @Bean
////    public UserDetailsService userDetailsService(){
////
////
////        UserDetails user1 = User
////                .withDefaultPasswordEncoder()
////                .username("Kiran")
////                .password("qwerty")
////                .roles("USER")
////                .build();
////
////        UserDetails user2 = User
////                .withDefaultPasswordEncoder()
////                .username("Harsh")
////                .password("qwerty2")
////                .roles("ADMIN")
////                .build();
////
////        return new InMemoryUserDetailsManager(user1,user2); // InMemoryUserDetailsManager is a class which implements UserDetailsManager which itself extends UserDetailsService .  So after manually creating the UserDetailsService we pass InMemoryUserDetailService as it is a class and it's object can be passed
////    }

}
