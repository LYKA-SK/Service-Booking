package com.mindvault.online_service.controller;

import com.mindvault.online_service.entities.Availability;
import com.mindvault.online_service.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
@io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuth")
public class AvailabilityController {

    @Autowired private AvailabilityService availabilityService;

    @PostMapping
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<String> setAvailability(@RequestBody List<Availability> slots, Authentication auth) {
        availabilityService.saveAvailability(auth.getName(), slots);
        return ResponseEntity.ok("Schedule saved successfully!");
    }
}