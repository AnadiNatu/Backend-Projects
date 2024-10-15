package com.pokemon.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    private JWTGenerator jwtGenerator;
    private CustomerUserDetailService customerUserDetailService;

    @Autowired
    public JWTAuthenticationFilter() {
        this.jwtGenerator = jwtGenerator;
        this.customerUserDetailService = customerUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getJWTfromRequest(request);

        if(StringUtils.hasText(token) && jwtGenerator.validatesToken(token)) {

            String username = jwtGenerator.generateUsernameFromJwt(token);

            UserDetails userDetails = customerUserDetailService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(request,response);

    }

    private String getJWTfromRequest(HttpServletRequest request){

        String bearerToken = request.getHeader("Autherization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7 , bearerToken.length());
        }

            return null;

    }
}
