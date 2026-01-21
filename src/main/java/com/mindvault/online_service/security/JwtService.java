package com.mindvault.online_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key; // Import this for 'Key'
import java.util.Date;

@Service
public class JwtService {

    // Ensure this key is exactly 32 characters or more
    private static final String SECRET_KEY = "your-32-character-secret-key-at-least-!!!";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Generate token
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                .signWith(key) 
                .compact();
    }

    // Extract email
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // Check if token is valid
    public boolean isTokenValid(String token) {
        return extractClaims(token).getExpiration().after(new Date());
    }

    // Public method for extracting claims (for your filter)
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}