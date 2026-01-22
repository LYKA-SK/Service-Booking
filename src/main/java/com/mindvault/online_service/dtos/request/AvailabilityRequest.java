package com.mindvault.online_service.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AvailabilityRequest {
    
    @Schema(example = "2026-01-22")
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", example = "09:00:00") 
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", example = "17:00:00")
    private LocalTime endTime;
}