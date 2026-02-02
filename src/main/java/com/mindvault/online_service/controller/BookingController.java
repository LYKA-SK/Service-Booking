package com.mindvault.online_service.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindvault.online_service.dtos.request.BookingRequest;
import com.mindvault.online_service.dtos.response.BookingResponse;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.security.CurrentUser;
import com.mindvault.online_service.service.BookingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> create(
            @Valid @RequestBody BookingRequest request,
            @CurrentUser User user) {

        BookingResponse response = bookingService.createBooking(request, user);
        return ResponseEntity
                .created(URI.create("/api/bookings/" + response.getId()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<List<BookingResponse>> getMyBookings(@CurrentUser User user) {
        return ResponseEntity.ok(bookingService.getMyBookings(user));
    }

    @PutMapping("/{id}/cancel")
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
