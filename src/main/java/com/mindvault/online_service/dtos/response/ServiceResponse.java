package com.mindvault.online_service.dtos.response;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceResponse {

    private Long id;
    private Long providerId;
    private Long categoryId;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer durationMinutes;
    private Boolean isActive;
}
