package com.yugmehta.auth_service.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {                            //this class generates jwt token using generateToken method

    private static final String SECRET_KEY =  "my-secret-key-my-secret-key-my-secret-key";
    private static final long EXPIRATION_TIME = 1000*60*60;

    public String generateToken(String username){
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).compact();
    }

    // 🔎 Extract Username
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 🔎 Extract Expiration
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 🔎 Generic Claim Extractor
    public <T> T extractClaim(String token,
                              Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // 🔐 Validate Token
    public boolean validateToken(String token,
                                 UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token));
    }

    // ⏳ Check Expiration
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 🔑 Signing Key
    private javax.crypto.SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

}
