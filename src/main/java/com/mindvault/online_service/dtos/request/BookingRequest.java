package com.mindvault.online_service.dtos.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingRequest {
    private Long serviceId;
    private LocalDate bookingDate;
    private LocalTimeDTO startTime;
    private LocalTimeDTO endTime;
}
