package com.mindvault.online_service.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", example = "09:00:00")     private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", example = "17:00:00")
    private LocalTime endTime;
}