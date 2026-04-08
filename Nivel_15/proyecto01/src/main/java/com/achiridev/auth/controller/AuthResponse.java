package com.achiridev.auth.controller;

public class AuthResponse {  
    private String token;

    public AuthResponse() {}
    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public String setToken(String token) {
        return this.token = token;
    }
}