package com.mindvault.online_service.service;

import com.mindvault.online_service.dtos.request.BookingRequest;
import com.mindvault.online_service.dtos.response.BookingResponse;
import com.mindvault.online_service.entities.User;
import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request, User customer);
    BookingResponse getBookingById(Long id);
    List<BookingResponse> getMyBookings(User user);
    BookingResponse approveBooking(Long bookingId, User approver);
    BookingResponse rejectBooking(Long bookingId, User approver);
    void cancelBooking(Long bookingId, User customer);
}
