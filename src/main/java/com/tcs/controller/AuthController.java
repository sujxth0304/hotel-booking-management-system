package com.tcs.controller;

import com.tcs.dto.AuthResponse;
import com.tcs.dto.LoginRequest;
import com.tcs.dto.RegisterRequest;
import com.tcs.entity.Role;
import com.tcs.entity.UserAccount;
import com.tcs.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest req,
                                 @RequestParam(defaultValue = "CUSTOMER") Role role) {
        return authService.register(req, role);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest req) {
        return authService.login(req);
    }

    @GetMapping("/me")
    public UserAccount me(@AuthenticationPrincipal UserAccount user) {
        return user; // returns current user info
    }
}
