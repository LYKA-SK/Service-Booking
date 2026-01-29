package com.mindvault.online_service.repositories;

import com.mindvault.online_service.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // fetch all bookings by customer
    List<Booking> findByCustomerId(Long customerId);
    
}
