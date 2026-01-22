package com.mindvault.online_service.controller;

import com.mindvault.online_service.dtos.request.AvailabilityRequest;
import com.mindvault.online_service.dtos.response.AvailabilityResponse;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @PostMapping
    public ResponseEntity<AvailabilityResponse> setAvailability(
            @RequestBody AvailabilityRequest request,
            @AuthenticationPrincipal User currentUser) { 
        // Automatically uses the ID from the Login Token (whether Admin or Provider)
        return ResponseEntity.ok(availabilityService.saveAvailability(request, currentUser.getId()));
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<List<AvailabilityResponse>> getServiceAvailability(@PathVariable Long serviceId) {
        return ResponseEntity.ok(availabilityService.getAvailabilityByService(serviceId));
    }
}