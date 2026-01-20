package com.mindvault.online_service.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    private Long userId; // Required based on your Swagger screenshot
}