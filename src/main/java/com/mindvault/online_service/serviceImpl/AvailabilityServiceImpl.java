package com.mindvault.online_service.serviceImpl;

import com.mindvault.online_service.dtos.request.AvailabilityRequest;
import com.mindvault.online_service.dtos.response.AvailabilityResponse;
import com.mindvault.online_service.entities.Availability;
import com.mindvault.online_service.entities.ServiceEntity;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.exception.ResourceNotFoundException;
import com.mindvault.online_service.repositories.AvailabilityRepository;
import com.mindvault.online_service.repositories.ServiceRepository;
import com.mindvault.online_service.repositories.UserRepository;
import com.mindvault.online_service.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;

    @Override
    @Transactional
    public AvailabilityResponse saveAvailability(AvailabilityRequest request, Long providerId) {
        // Automatically finds the user (Admin or Provider) from the Login Token
        User provider = userRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("User not found: " + providerId));

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
        // 1. Find the service
        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service ID " + serviceId + " not found"));

        // 2. Identify the owner (Admin or Provider)
        User owner = service.getProvider();

        // 3. Search for slots belonging to THAT specific person
        List<Availability> availabilities = availabilityRepository.findByProviderId(owner.getId());

        // 4. If empty, throw the specific exception your Global Handler is looking for
        if (availabilities.isEmpty()) {
            throw new ResourceNotFoundException("The user '" + owner.getFullName() + 
                "' (ID: " + owner.getId() + ") has no schedule saved yet.");
        }

        return availabilities.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    private AvailabilityResponse mapToResponse(Availability availability) {
        return AvailabilityResponse.builder()
                .id(availability.getId())
                .providerName(availability.getProvider().getFullName())
                .date(availability.getDate())
                .startTime(availability.getStartTime()) 
                .endTime(availability.getEndTime())
                .build();
    }
}