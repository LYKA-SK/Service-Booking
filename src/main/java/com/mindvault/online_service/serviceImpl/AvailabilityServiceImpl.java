package com.mindvault.online_service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindvault.online_service.dtos.request.AvailabilityRequest;
import com.mindvault.online_service.dtos.response.AvailabilityResponse;
import com.mindvault.online_service.entities.Availability;
import com.mindvault.online_service.entities.ServiceEntity;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.repositories.AvailabilityRepository;
import com.mindvault.online_service.repositories.ServiceRepository;
import com.mindvault.online_service.repositories.UserRepository;
import com.mindvault.online_service.service.AvailabilityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;

    @Override
    @Transactional
    public AvailabilityResponse saveAvailability(AvailabilityRequest request, Long providerId) {
        User provider = userRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        Availability availability = Availability.builder()
                .provider(provider)
                .date(request.getDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();

        return mapToResponse(availabilityRepository.save(availability));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AvailabilityResponse> getAvailabilityByService(Long serviceId) {
        // This finds the service your team is building
        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        
        // This finds the availability hours YOU are building
        return availabilityRepository.findByProviderId(service.getProvider().getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private AvailabilityResponse mapToResponse(Availability availability) {
        return AvailabilityResponse.builder()
                .id(availability.getId())
                // Fixed: Now uses getFullName() from your updated User entity
                .providerName(availability.getProvider().getFullName()) 
                .date(availability.getDate())
                .startTime(availability.getStartTime())
                .endTime(availability.getEndTime())
                .build();
    }
}