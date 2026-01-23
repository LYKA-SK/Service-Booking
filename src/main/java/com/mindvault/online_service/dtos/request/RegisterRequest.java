package com.mindvault.online_service.dtos.request;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Data
public class RegisterRequest {
    @Schema(description = "Full name of the user")
    
	private String fullName;
    @Schema(description = "Email of the user")
    private String email;
    @Schema(description = "Password of the user")
    private String password;
}