package com.mindvault.online_service.dtos.request;

import lombok.Data;

@Data
public class RegisterRequest {
	private String fullName;
    private String email;
    private String password;
}