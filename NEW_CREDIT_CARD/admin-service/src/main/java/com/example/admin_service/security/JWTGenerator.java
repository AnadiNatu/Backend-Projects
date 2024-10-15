package com.example.admin_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class JWTGenerator {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;


    public String generateToken(String username){

        Map<String , Object> claims = new HashMap<>();

        return createToken(claims,username);
    }

    private String createToken(Map<String,Object> claims , String subject){

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.ES256 , jwtSecret)
                .compact();
    }

    public boolean validateToken(String token){

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);

            return true;
        }catch (Exception ex){

            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }

    public String getUsernameByJwt(String token){

        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }
}
