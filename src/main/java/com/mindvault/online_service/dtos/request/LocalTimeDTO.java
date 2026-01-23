package com.mindvault.online_service.dtos.request;

import lombok.Data;
import java.time.LocalTime;

@Data
public class LocalTimeDTO {
    private int hour;
    private int minute;
    private int second;
    private int nano;

    public LocalTime toLocalTime() {
        return LocalTime.of(hour, minute, second, nano);
    }
}
