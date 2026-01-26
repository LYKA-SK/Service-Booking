package com.mindvault.online_service.controller;

import com.mindvault.online_service.dtos.request.BookingRequest;
import com.mindvault.online_service.dtos.response.BookingResponse;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.security.CurrentUser;
import com.mindvault.online_service.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // -------------------- CREATE BOOKING (Customer only) --------------------
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<BookingResponse> create(
            @Valid @RequestBody BookingRequest request,
            @CurrentUser User user) {

        BookingResponse response = bookingService.createBooking(request, user);
        return ResponseEntity
                .created(URI.create("/api/bookings/" + response.getId()))
                .body(response);
    }

    // -------------------- GET BOOKING BY ID --------------------
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    // -------------------- GET MY BOOKINGS (Customer only) --------------------
    @GetMapping("/me")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<BookingResponse>> getMyBookings(@CurrentUser User user) {
        return ResponseEntity.ok(bookingService.getMyBookings(user));
    }

    // -------------------- CANCEL BOOKING (Customer only) --------------------
    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> cancel(
            @PathVariable Long id,
            @CurrentUser User user) {
        bookingService.cancelBooking(id, user);
        return ResponseEntity.noContent().build();
    }

    // -------------------- APPROVE BOOKING (Admin/Provider only) --------------------
    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVIDER')")
    public ResponseEntity<BookingResponse> approve(
            @PathVariable Long id,
            @CurrentUser User user) {
        BookingResponse response = bookingService.approveBooking(id, user);
        return ResponseEntity.ok(response);
    }

    // -------------------- REJECT BOOKING (Admin/Provider only) --------------------
    @PutMapping("/{id}/reject")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVIDER')")
    public ResponseEntity<BookingResponse> reject(
            @PathVariable Long id,
            @CurrentUser User user) {
        BookingResponse response = bookingService.rejectBooking(id, user);
        return ResponseEntity.ok(response);
    }
}
