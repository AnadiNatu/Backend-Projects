package com.pokemon.api.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTGenerator {

    public Stri ng generateToken(Authentication authentication){

        String username = authentication.getName();

        Date currentDate = new Date();
        //we need date because we need to set the expiration date for the JWT

        Date expireDate = new Date(currentDate.getTime()
                + SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.JWT_SECRET)
                .compact();

        return token;

    }

    public String generateUsernameFromJwt(String token){

        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJwt(token)
                .getBody();

        return claims.getSubject();

    }

    public boolean validatesToken(String token){

        try {

            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJwt(token);

            return true;

        }catch (Exception ex){

            throw new AuthenticationCredentialsNotFoundException("JWT has expired or incorrect");

        }

    }

}
