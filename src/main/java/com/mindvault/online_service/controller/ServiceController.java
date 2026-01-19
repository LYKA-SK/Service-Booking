package com.mindvault.online_service.controller;

import com.mindvault.online_service.dtos.request.ServiceRequest;
import com.mindvault.online_service.dtos.response.ServiceResponse;
import com.mindvault.online_service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping
    public ResponseEntity<ServiceResponse> createService(@RequestBody ServiceRequest request,
                                                         @RequestParam Long userId) {
        return ResponseEntity.ok(serviceService.createService(request, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> updateService(@PathVariable Long id,
                                                         @RequestBody ServiceRequest request,
                                                         @RequestParam Long userId) {
        return ResponseEntity.ok(serviceService.updateService(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id,
                                              @RequestParam Long userId) {
        serviceService.deleteService(id, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getService(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.getServiceById(id));
    }

    @GetMapping
    public ResponseEntity<List<ServiceResponse>> getAllServices() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<ServiceResponse>> getServicesByProvider(@PathVariable Long providerId) {
        return ResponseEntity.ok(serviceService.getServicesByProvider(providerId));
    }
}
