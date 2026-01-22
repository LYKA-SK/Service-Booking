package com.mindvault.online_service.dtos.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceRequest {
    private Long categoryId;
    private String title;
    private String description;
    private BigDecimal price;
    private int durationMinutes;
}
