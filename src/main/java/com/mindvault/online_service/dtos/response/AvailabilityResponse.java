package com.mindvault.online_service.dtos.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class AvailabilityResponse {
    private Long id;
    private String providerName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}