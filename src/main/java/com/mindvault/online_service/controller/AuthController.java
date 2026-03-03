package com.mindvault.online_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindvault.online_service.dtos.request.LoginRequest;
import com.mindvault.online_service.dtos.request.RegisterRequest;
import com.mindvault.online_service.dtos.response.AuthResponse;
import com.mindvault.online_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user registration and login")

public class AuthController {

    private final AuthService authService;
    
    

    @PostMapping("/register")
        @Operation(summary = "Register a new user", description = "Create a new user with full name, email, and password")

    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
        @Operation(summary = "Login a user", description = "Authenticate a user with email and password")

    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}