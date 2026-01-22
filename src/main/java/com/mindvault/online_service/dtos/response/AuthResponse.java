package com.mindvault.online_service.dtos.response;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    @Schema(description = "JWT token for authentication")
        private String token;
    @Schema(description = "User email")
        private String email;
    @Schema(description = "User role")
        private String role;
}