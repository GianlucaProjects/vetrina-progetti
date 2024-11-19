package com.cerretagianluca.vetrina_progetti.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JWTTools {
    @Value("spring.jwt.secret")
    String secret;

    public void verifyToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception e) {
            throw new RuntimeException("Token invalid!");
        }
    }

    public String decodeToken(String token) {
        return Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token).getPayload().getSubject();
    }

    public String createToken(UUID id) {
        return Jwts.builder().subject(id.toString()).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15)).signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
    }
}
