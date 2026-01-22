package com.mindvault.online_service.dtos.request;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AvailabilityRequest {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}