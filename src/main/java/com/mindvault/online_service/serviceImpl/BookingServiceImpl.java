package com.mindvault.online_service.serviceImpl;

import com.mindvault.online_service.dtos.request.BookingRequest;
import com.mindvault.online_service.dtos.response.BookingResponse;
import com.mindvault.online_service.entities.Booking;
import com.mindvault.online_service.entities.BookingStatus;
import com.mindvault.online_service.entities.ServiceEntity;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.exception.ResourceNotFoundException;
import com.mindvault.online_service.repositories.BookingRepository;
import com.mindvault.online_service.repositories.ServiceRepository;
import com.mindvault.online_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ServiceRepository serviceRepository;

    // -------------------- CREATE BOOKING --------------------
    @Override
    public BookingResponse createBooking(BookingRequest request, User customer) {
        ServiceEntity service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        Booking booking = Booking.builder()
                .customer(customer)
                .service(service)
                .bookingDate(request.getBookingDate())
                .startTime(request.getStartTime().toLocalTime())
                .endTime(request.getEndTime().toLocalTime())
                .status(BookingStatus.PENDING)
                .build();

        Booking saved = bookingRepository.save(booking);
        return BookingResponse.fromEntity(saved);
    }

    // -------------------- GET BOOKING BY ID --------------------
    @Override
    public BookingResponse getBookingById(Long id) {
        return BookingResponse.fromEntity(getBooking(id));
    }

    // -------------------- GET CUSTOMER BOOKINGS --------------------
    @Override
    public List<BookingResponse> getMyBookings(User user) {
        return bookingRepository.findByCustomerId(user.getId())
                .stream()
                .map(BookingResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // -------------------- CANCEL BOOKING --------------------
    @Override
    public void cancelBooking(Long bookingId, User customer) {
        Booking booking = getBooking(bookingId);

        if (!booking.getCustomer().getId().equals(customer.getId())) {
            throw new AccessDeniedException("You can only cancel your own booking");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    // -------------------- APPROVE BOOKING --------------------
    @Override
    public BookingResponse approveBooking(Long bookingId, User approver) {
        Booking booking = getBooking(bookingId);

        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new IllegalStateException("Only pending bookings can be approved");
        }

        booking.setStatus(BookingStatus.APPROVED);
        bookingRepository.save(booking);
        return BookingResponse.fromEntity(booking);
    }

    // -------------------- REJECT BOOKING --------------------
    @Override
    public BookingResponse rejectBooking(Long bookingId, User approver) {
        Booking booking = getBooking(bookingId);

        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new IllegalStateException("Only pending bookings can be rejected");
        }

        booking.setStatus(BookingStatus.REJECTED);
        bookingRepository.save(booking);
        return BookingResponse.fromEntity(booking);
    }

    // -------------------- HELPER METHOD --------------------
    private Booking getBooking(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }
}
