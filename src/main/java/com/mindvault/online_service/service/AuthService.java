package com.mindvault.online_service.service;

import com.mindvault.online_service.dtos.request.RegisterRequest;
import com.mindvault.online_service.dtos.request.LoginRequest;
import com.mindvault.online_service.dtos.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
