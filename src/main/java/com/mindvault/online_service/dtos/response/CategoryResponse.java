package com.mindvault.online_service.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private String createdBy; // Shows Admin name instead of full User object
}