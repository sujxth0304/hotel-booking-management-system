package com.tcs.dto;

public class AuthResponse {
    public String token;
    public String role;
    public String email;
    public String fullName;

    public AuthResponse(String token, String role, String email, String fullName) {
        this.token = token; this.role = role; this.email = email; this.fullName = fullName;
    }
}
