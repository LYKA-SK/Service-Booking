package com.mindvault.online_service.controller;

import com.mindvault.online_service.dtos.request.ServiceRequest;
import com.mindvault.online_service.dtos.response.ServiceResponse;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.security.CurrentUser;
import com.mindvault.online_service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

   private final ServiceService serviceService;

    @GetMapping
    public ResponseEntity<List<ServiceResponse>> getAll() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.getServiceById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    public ResponseEntity<ServiceResponse> create(
            @RequestBody ServiceRequest request,
            @CurrentUser User creator) {
        return ResponseEntity.ok(serviceService.createService(request, creator));
    }

    // ✅ NEW: Update service
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    public ResponseEntity<ServiceResponse> updateService(
            @PathVariable Long id,
            @RequestBody ServiceRequest request) {
        return ResponseEntity.ok(serviceService.updateService(id, request));
    }
   
@DeleteMapping("/{id}")
@PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
public ResponseEntity<Void> deleteService(@PathVariable Long id) {
    serviceService.deleteService(id);
    return ResponseEntity.noContent().build();
}


    
}
