package com.tcs.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tcs.dto.AuthResponse;
import com.tcs.dto.LoginRequest;
import com.tcs.dto.RegisterRequest;
import com.tcs.entity.Role;
import com.tcs.entity.UserAccount;
import com.tcs.repository.UserRepository;
import com.tcs.security.JwtService;

import java.util.Map;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest req, Role role) {
        if (userRepo.findByEmail(req.email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
        }

        UserAccount user = new UserAccount();
        user.setEmail(req.email);
        user.setPasswordHash(passwordEncoder.encode(req.password));
        user.setFullName(req.fullName);
        user.setPhone(req.phone);
        user.setRole(role);
        userRepo.save(user);

        String token = jwtService.generateToken(user.getEmail(), Map.of(
            "role", user.getRole().name(), 
            "uid", user.getId()
        ));
        
        return new AuthResponse(token, user.getRole().name(), user.getEmail(), user.getFullName());
    }

    public AuthResponse login(LoginRequest req) {
        UserAccount user = userRepo.findByEmail(req.email)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!passwordEncoder.matches(req.password, user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail(), Map.of(
            "role", user.getRole().name(), 
            "uid", user.getId()
        ));

        return new AuthResponse(token, user.getRole().name(), user.getEmail(), user.getFullName());
    }

    public UserAccount getByEmail(String email) {
        return userRepo.findByEmail(email)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}