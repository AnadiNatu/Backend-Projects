package com.pokemon.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtGenerator jwtGenerator;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getJWTFromRequest(request);

        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)){

            String username =  jwtGenerator.getUsernameFromJwt(token);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());


            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            //This line what is does is that , it lets the user login after which application will not ask to re-login after every api endpoint change

        }

        filterChain.doFilter(request,response);

    }

    private String getJWTFromRequest( HttpServletRequest request ) {

        String bearerToken = request.getHeader("Authentication");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){

            return bearerToken.substring(7 , bearerToken.length());

        }

        return null ;

    }



}
