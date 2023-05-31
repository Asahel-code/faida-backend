package com.example.Faida.response;

import com.example.Faida.models.User;

public class JwtAuthenticationResponse {
    private String token;
    private User user;

    public JwtAuthenticationResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return user.getFirstName() + " " + user.getLastName();
    }

    public void setToken(String token) {
        this.token = token;
    }
}
