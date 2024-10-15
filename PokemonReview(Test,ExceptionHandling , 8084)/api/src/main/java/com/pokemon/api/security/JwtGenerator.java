package com.pokemon.api.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


public class JwtGenerator {

    public String generateToken(Authentication authentication){

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + SecurityConstant.JWT_EXPIRATION);


        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.ES256 , SecurityConstant.JWT_SECRET)
                .compact();
        return token;

        //this is the part where the JWT is actually made , lifecycle of the token is set here along with
        //the signature or the secret key that is used to encode the UserDetails at the time of login
    }


    public String getUsernameFromJwt(String token){

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SecurityConstant.JWT_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token){

        try {
            Jwts.parserBuilder().setSigningKey(SecurityConstant.JWT_SECRET).build().parseClaimsJwt(token);
            return true;
        }catch (Exception ex){
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }
}
