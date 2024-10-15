package com.example.NewSecurityJwt.config;


import com.example.NewSecurityJwt.filter.JwtAuthFilter;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // this annotation tells spring boot that we also implemented authentication n the methods of the controller
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter authFilter;

    @Autowired
    private UserInfoUserDetailService userDetailService;

//    @Bean
//    //authentication
//    public UserDetailsService userDetailsService() {
////        UserDetails admin = User.withUsername("Basant")
////                .password(encoder.encode("Pwd1"))
////                .roles("ADMIN")
////                .build();
////        UserDetails user = User.withUsername("John")
////                .password(encoder.encode("Pwd2"))
////                .roles("USER","ADMIN","HR")
////                .build();
////        return new InMemoryUserDetailsManager(admin, user);
//        return new UserInfoUserDetailService();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(request -> request.disable())
                .authorizeHttpRequests(request -> request.requestMatchers("products/new" , "products/authenticate").permitAll()
                        .requestMatchers("/products/all" , "/products/{id}").authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter , UsernamePasswordAuthenticationFilter.class)
                .build();


        //        return http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/products/new","/products/authenticate").permitAll()
//                .and()
//                .authorizeHttpRequests().requestMatchers("/products/**")
//                .authenticated().and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}











































//{
//
//
//    @Autowired
//    private JwtAuthFilter authFilter;
//
//    @Bean
//    //Authentication
//    public UserDetailsService userDetailsService(){
////
////        UserDetails admin = User.withUserDetails("Anadi")
////                .password(encoder.encode("Pwd1"))
////                .roles("ADMIN").build();
////
////        UserDetails user = User.withUserDetails("John")
////                .password(encoder.encode("Pwd2"))
////                .roles("ADMIN" ,"USER","HR").build();
////
////
////        return new UserInfo
//        return new UserInfoUserDetailService(); // We want our UserDetailService class to interact with the database
//    }
//
//
//
//    //Now after we implement the filterchain logic we need to tell spring security that we have created a filter , use that filter before you use any of your filter
//
//    @Bean
//    //Authorization
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//
//        return http.csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/products/new" , "/products/authenticate").permitAll()
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/products/**")
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)// this line prevents cookies of our session after it is ended
//                .and()
//                .authenticationProvider(authenticationProvider()) // and we are providing our custom implemented authentication provider
//                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)// in this line we are saying that first execute our filter then UsernameNamePasswordFilter
//                .build();
//
//
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//
//        return new BCryptPasswordEncoder();
//    }
//
//
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(){  // What this AuthenticationProvider does is that it  creates a UserDetail object
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//
//        authenticationProvider.setUserDetailsService(userDetailsService());
//
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//
//        return authenticationProvider;
//    }
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//     //(10)
//     //AuthenticationManager is a core interface in Spring Security that is responsible for processing authentication requests . If checks the provided credentials(like username and password) against the stored data ,in DB . To determine if the user is authentic
//     // method, the configuration.getAuthenticationManager() call retrieves the AuthenticationManager from the given AuthenticationConfiguration. This AuthenticationManager is then used to handle authentication requests in the application.
//     // Flow - Authentication Manager is called from the authentication controller , when user submits their login credentials . It checks the credential and returns an authentication result
//
//        return configuration.getAuthenticationManager();
//    }
//
//    // We have to create all the beans manually for the implementation of web security
//}
