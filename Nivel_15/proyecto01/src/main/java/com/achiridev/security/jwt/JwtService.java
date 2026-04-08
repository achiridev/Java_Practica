package com.achiridev.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

    private final JwtConfig jwtConfig;
    private final SecretKey key;

    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.key = Keys.hmacShaKeyFor(
                jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(String email, List<String> roles) {

        Instant now = Instant.now();

        List<String> rolesSinPrefijo = roles.stream()
                .map(role -> role.startsWith("ROLE_") ? role.substring(5) : role)
                .toList();

        return Jwts.builder()
                .subject(email)
                .claim("roles", rolesSinPrefijo)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(jwtConfig.getExpiration())))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {  
        return extractClaims(token).getSubject();  
    }

    public boolean validateToken(String token, UserDetails userDetails) {  
        String email = extractEmail(token);  
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);  
    }

    private boolean isTokenExpired(String token) {  
        return extractClaims(token).getExpiration().before(new Date());  
    }

    private Claims extractClaims(String token) {  
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
