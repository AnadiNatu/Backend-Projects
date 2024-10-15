package com.example.Task_SpringBoot.config;

import com.example.Task_SpringBoot.services.jwt.UserServiceImpl;
import com.example.Task_SpringBoot.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        username = jwtUtil.extractUsername(jwt);
        if ((!StringUtils.isEmpty(username)) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(username);
            if (jwtUtil.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);

    }
}




//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
//
//        if ( authHeader != null && authHeader.startsWith("Bearer ")){
//            token = authHeader.substring(7);
//            username = jwtUtil.extractUsername(token);
//
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
//
//            UserDetails userDetails = userService.loadUserByUsername(username); // this is to get the users details from the db using an instances of the MyUserDetailService class (custom UserDetail class)
//
//            if (jwtUtil.isTokenValid(token , userDetails)){
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



