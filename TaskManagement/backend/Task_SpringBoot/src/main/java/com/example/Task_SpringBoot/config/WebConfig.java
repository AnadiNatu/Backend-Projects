package com.example.Task_SpringBoot.config;

import com.example.Task_SpringBoot.enums.UserRole;
import com.example.Task_SpringBoot.services.jwt.UserServiceImpl;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebConfig {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .csrf(request -> request.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(request -> request.requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/admin/**").hasAnyAuthority(UserRole.ADMIN.name())
                        .requestMatchers("/api/employee/**").hasAnyAuthority(UserRole.EMPLOYEE.name())
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class)
                .build();



        //        return  http.csrf(customizer -> customizer.disable())
//                .authorizeHttpRequests(request -> request.requestMatchers("/api/auth/**").permitAll()
//                        .requestMatchers("/api/admin/**").hasAnyAuthority(UserRole.ADMIN.name())
//                        .requestMatchers("/api/employee/**").hasAnyAuthority(UserRole.EMPLOYEE.name())
//                        .anyRequest().authenticated())
//                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class)
//                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);

        return provider;
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfig.setAllowedMethods(Arrays.asList("GET","POST" ,"PUT" , "DELETE" , "OPTION"));
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization" , "Content-Type"));
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**" , corsConfig);

        return source;
    }

//    CORS filter bean is optional , can be used if needed outside SecurityFilterChain
//    @Bean
//    public CorsFilter corsFilter(){
//        return new CorsFilter();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }
}
