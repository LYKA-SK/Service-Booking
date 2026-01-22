package com.mindvault.online_service.controller;

import com.mindvault.online_service.entities.Availability;
import com.mindvault.online_service.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    // POST: Provider sets their working hours
    @PostMapping
    public ResponseEntity<Availability> setAvailability(@RequestBody Availability availability) {
        // Logic to get current logged-in provider ID would go here
        Long mockProviderId = 1L; 
        return ResponseEntity.ok(availabilityService.saveAvailability(availability, mockProviderId));
    }

    // GET: Customer views availability for a specific service
    @GetMapping("/{service_id}")
    public ResponseEntity<List<Availability>> getServiceAvailability(@PathVariable Long service_id) {
        return ResponseEntity.ok(availabilityService.getAvailabilityByService(service_id));
    }
}