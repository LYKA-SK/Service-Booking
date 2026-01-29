package com.mindvault.online_service.dtos.response;

import com.mindvault.online_service.entities.Booking;
import com.mindvault.online_service.entities.BookingStatus;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingResponse {
    private Long id;
    private Long serviceId;
    private Long customerId;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private BookingStatus status;

    public static BookingResponse fromEntity(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setId(booking.getId());
        response.setServiceId(booking.getService().getId());
        response.setCustomerId(booking.getCustomer().getId());
        response.setBookingDate(booking.getBookingDate());
        response.setStartTime(booking.getStartTime());
        response.setEndTime(booking.getEndTime());
        response.setStatus(booking.getStatus());
        return response;
    }
}
