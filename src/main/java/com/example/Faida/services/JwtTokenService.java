package com.example.Faida.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import com.example.Faida.config.JwtConfig;
import com.example.Faida.models.User;

@Service
public class JwtTokenService {

    private final JwtConfig jwtConfig;

    @Autowired
    public JwtTokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(User user) {
        Instant now = Instant.now();
        Instant expiryInstant = now.plus(Duration.ofMillis(jwtConfig.getExpiration()));

        Claims claims = Jwts.claims().setSubject(user.getPhoneNumber());

        Key signingKey = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiryInstant))
                .signWith(signingKey)
                .compact();


    }
}
