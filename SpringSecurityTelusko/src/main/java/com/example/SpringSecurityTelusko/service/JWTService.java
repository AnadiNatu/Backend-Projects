package com.example.SpringSecurityTelusko.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {


    private String secretkey = "";

    public JWTService() {

        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretkey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                .and()
                .signWith(getKey())
                .compact();

    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
































//    private static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
//
//
//    // Creating a random SHA256 compatible secret key
//
//    public JWTService(){
//        // making it in the constructor and asigning it to the constant.
////        try{
////            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
////            SecretKey sk = keyGen.generateKey();
////            SECRET=Base64.getEncoder().encodeToString(sk.getEncoded());
////        } catch (NoSuchAlgorithmException e) {
////            throw new RuntimeException(e);
////        }
//    }
//
//
//    public String generateToken(String username) {
//
//        Map<String,Object> claims = new HashMap<>();
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+86400000))
//                .and()
//                .signWith(getSigningKey())
//                .compact();
//    }
//
//    private SecretKey getSigningKey() {
//
//        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET);  // this line of code converts the string into a string array for .hmacShaKeyFor() method
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//
//    public String extractUsername(String token) {
//        return extractClaim(token , Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
//
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token){
//        return Jwts.parser()
//                .verifyWith(getSigningKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    private boolean isTokenExpired(String token){
//        return  extractExpiration(token).before(new Date());
//    }
//    private Date extractExpiration(String token){
//        return extractClaim(token , Claims::getExpiration);
//    }
}
