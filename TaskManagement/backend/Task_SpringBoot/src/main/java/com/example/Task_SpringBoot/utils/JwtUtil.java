package com.example.Task_SpringBoot.utils;

import com.example.Task_SpringBoot.entities.Users;
import com.example.Task_SpringBoot.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    @Autowired
    private UserRepository userRepository;

    // Creating a random SHA256 compatible secret key

    public JwtUtil(){
        // making it in the constructor and asigning it to the constant.
//        try{
//            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//            SecretKey sk = keyGen.generateKey();
//            SECRET= Base64.getEncoder().encodeToString(sk.getEncoded());
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
    }




    public String generateToken(String username) {

        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*30*60))
                .and()
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {

        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET);  // this line of code converts the string into a string array for .hmacShaKeyFor() method
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String extractUsername(String token) {
        return extractClaim(token , Claims::getSubject);
    }

    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){

        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return  extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaim(token , Claims::getExpiration);
    }


    public Users getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()){
            Users user = (Users) authentication.getPrincipal();
            Optional<Users> optionalUser= userRepository.findById(user.getId());
            return optionalUser.orElse(null);
        }

        return null;
    // THis method will fetch the currently logged in user from the security context fetch the corresponding user from the database and return user if the user is currently logged in and return null if there is no user in the database then return null

    }

}
