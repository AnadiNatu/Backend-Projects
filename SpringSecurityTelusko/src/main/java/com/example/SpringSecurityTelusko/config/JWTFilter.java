package com.example.SpringSecurityTelusko.config;

import com.example.SpringSecurityTelusko.service.JWTService;
import com.example.SpringSecurityTelusko.service.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//  Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraWxsIiwiaWF0IjoxNzIzMTgzNzExLCJleHAiOjE3MjMxODM4MTl9.5nf7dRzKRiuGurN2B9dHh_M5xiu73ZzWPr6rbhOTTHs
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUserName(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = context.getBean(MyUserDetailService.class).loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }












































//    @Autowired
//    private JWTService jwtService;
//
//    @Autowired
//    private ApplicationContext context;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
//
////        if (authHeader.startsWith("Bearer ")  && authHeader != null)
//        if ( authHeader != null  &&  authHeader.startsWith("Bearer ")){
//            // the error over here is that
//            //Condition Order: The original condition authHeader.startsWith("Bearer ") && authHeader != null is problematic because it checks if authHeader starts with "Bearer " before confirming that authHeader is not null. If authHeader is null, the .startsWith("Bearer ") check would cause a NullPointerException.
//
//            token = authHeader.substring(7);
//            username = jwtService.extractUsername(token);
//
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
//
//            UserDetails userDetails = context.getBean(MyUserDetailService.class).loadUserByUsername(username); // this is to get the users details from the db using an instances of the MyUserDetailService class (custom UserDetail class)
//
//            if (jwtService.validateToken(token , userDetails)){
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities()); // this is for next UPAF
//                // this auth token has no idea about the the data coming in from the request
//
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request,response); // this line basically says that once you do a filter move to the next filter
//    }
}
