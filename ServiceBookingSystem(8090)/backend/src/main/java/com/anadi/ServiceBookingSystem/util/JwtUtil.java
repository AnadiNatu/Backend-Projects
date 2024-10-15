package com.anadi.ServiceBookingSystem.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    public static String SECRET = "8858908086F128855555555575T7578585IU0079";

    public String createToken(Map<String,Object> claim , String userName){

        return Jwts.builder()
                .setClaims(claim)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(SignatureAlgorithm.HS256, getSignKey()).compact();
    }

    private Key getSignKey(){
        byte[] KeyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(KeyBytes);
    }

    public String generateToken(String userName){

        Map<String,Object> claims = new HashMap<>();

        return createToken(claims,userName);
    }

    private Claims extractAllClaims(String token){

        return Jwts.parser()
                .setSigningKey(getSignKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token , Function<Claims, T> claimResolver){

        final Claims claims = extractAllClaims(token);

        return claimResolver.apply(claims);

    }

    public Date extractExpiration(String token){

        return extractClaim(token , Claims::getExpiration);

    }

    public String extractUsername(String token){

        return extractClaim(token , Claims::getSubject);

    }

    private Boolean isTokenExpired(String token){

        return extractExpiration(token).before(new Date());

    }

    public Boolean validateToken(String token , UserDetails userDetails){

        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }
}
