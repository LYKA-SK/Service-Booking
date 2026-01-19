package com.mindvault.online_service.dtos.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequest {

    private Long categoryId;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer durationMinutes;
    private Boolean isActive;
}
