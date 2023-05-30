package com.example.Faida.config;

import org.springframework.context.annotation.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;

@Configuration
public class JwtConfig {

    private Dotenv dotenv;
    
    private String secret;
    private long expiration = 5000000;


    public JwtConfig() {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        dotenv = Dotenv.configure().load();
        secret = dotenv.get("JWT_SECRET");     
    }

    public String getSecret() {
        return secret;
    }

    public long getExpiration() {
        return expiration;
    }
}

