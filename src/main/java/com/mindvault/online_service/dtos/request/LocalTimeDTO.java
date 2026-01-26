package com.mindvault.online_service.dtos.request;

import lombok.Data;
import java.time.LocalTime;

@Data
public class LocalTimeDTO {
    private int hour;

    public LocalTime toLocalTime() {
        // Only hour is set, minutes, seconds, nanos are 0
        return LocalTime.of(hour, 0, 0, 0);
    }
}
