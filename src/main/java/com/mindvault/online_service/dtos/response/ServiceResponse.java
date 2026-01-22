package com.mindvault.online_service.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ServiceResponse {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private int durationMinutes;
    private Long providerId;
    private Long categoryId;
    private Boolean isActive;
}
