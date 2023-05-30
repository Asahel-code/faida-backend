package com.example.Faida.config;

import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class JwtConfig {

    Dotenv dotenv = Dotenv.configure().load();

    private String secret = dotenv.get("JWT_SECRET");

    private long expiration = 5000000;

    public String getSecret(){
        return secret;
    }

    public long getExpiration(){
        return expiration;
    }

}
