package com.mindvault.online_service.repositories;

import com.mindvault.online_service.entities.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    // Finds schedule specifically for the provider who owns the service
    List<Availability> findByProviderId(Long providerId);
}