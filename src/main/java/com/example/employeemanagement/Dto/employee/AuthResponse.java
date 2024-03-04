package com.example.employeemanagement.Dto.employee;

public class AuthResponse {

    private final String accessToken;
    private final String tokenType = "Bearer ";

    public AuthResponse(String token){
        this.accessToken = token;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
