package com.example.NewSecurityJwt.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
// All the parts of the JWT (header,payload,signature) all are known as claims in jwt
// Now to create the token we need signing key in signature part , issued&expired at time in body , and algorithm in the header so to add these and create a token we make a function
// In this function we are step by step creating the JWT setting the  fields of the jwt
// Last thing is setting of the certificate or signing key

// To generate the key we need a SECRET
// JwtUtil Class in Service Booking Application
// this function will return all the claims(components) of the JWT
public class JwtService {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String extractUsername(String token){
        return extractClaim(token , Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token , Claims::getExpiration);
    }

    public <T> T extractClaim(String token , Function<Claims , T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token , UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,username);
    }

    private String createToken(Map<String,Object> claims , String username){
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*30))
                .and()
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey(){
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
//(9)
// The JWTService class has the code responsible for handling JWT operations , such as extracting information from the token , validating the token , and generating a new token . It is central in implementing JWT-based authentication in a Spring Security application

// Without this code , the application would not be able to securely manage user sessions using JWTs




