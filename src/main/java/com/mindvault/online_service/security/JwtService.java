package com.mindvault.online_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    // Ensure this key is exactly 32 characters or more
    private static final String SECRET_KEY = "your-32-character-secret-key-at-least-!!!";
    
    // In 0.12.x, use SecretKey specifically
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // Generate token
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .subject(email) 
                .claim("role", role)
                .issuedAt(new Date()) 
                .expiration(new Date(System.currentTimeMillis() + 86400000)) 
                .signWith(key) 
                .compact();
    }

    // Extract email
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // Check if token is valid
    public boolean isTokenValid(String token) {
        try {
            return extractClaims(token).getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // JJWT 0.12.x
    public Claims extractClaims(String token) {
        return Jwts.parser()             
                .verifyWith(key)        
                .build()
                .parseSignedClaims(token) 
                .getPayload();           
    }
}