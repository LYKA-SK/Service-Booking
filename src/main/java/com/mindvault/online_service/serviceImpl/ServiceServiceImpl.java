package com.mindvault.online_service.serviceImpl;

import com.mindvault.online_service.dtos.request.ServiceRequest;
import com.mindvault.online_service.dtos.response.ServiceResponse;
import com.mindvault.online_service.entities.Category;
import com.mindvault.online_service.entities.ServiceEntity;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.exception.ResourceNotFoundException;
import com.mindvault.online_service.repositories.CategoryRepository;
import com.mindvault.online_service.repositories.ServiceRepository;
import com.mindvault.online_service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ServiceResponse createService(ServiceRequest request, User creator) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        ServiceEntity service = ServiceEntity.builder()
                .provider(creator)
                .category(category)
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .durationMinutes(request.getDurationMinutes())
                .isActive(true)
                .build();

        ServiceEntity saved = serviceRepository.save(service);

        return mapToResponse(saved);
    }

    @Override
    public List<ServiceResponse> getAllServices() {
        return serviceRepository.findByIsActiveTrue().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceResponse getServiceById(Long id) {
       ServiceEntity service = serviceRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Service not found with ID: " + id));


        return mapToResponse(service);
    }

    @Override
    public ServiceResponse updateService(Long serviceId, ServiceRequest request) {
        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found with ID: " + serviceId));

        // Update category if changed
        if (!service.getCategory().getId().equals(request.getCategoryId())) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            service.setCategory(category);
        }

        // Update other fields
        service.setTitle(request.getTitle());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());
        service.setDurationMinutes(request.getDurationMinutes());

        ServiceEntity updated = serviceRepository.save(service);

        return mapToResponse(updated);
    }

    // Helper method to convert ServiceEntity to ServiceResponse
    private ServiceResponse mapToResponse(ServiceEntity service) {
        return new ServiceResponse(
                service.getId(),
                service.getTitle(),
                service.getDescription(),
                service.getPrice(),
                service.getDurationMinutes(),
                service.getProvider().getId(),
                service.getCategory().getId(),
                service.getIsActive()
        );
    }

@Override
public void deleteService(Long serviceId) {
    ServiceEntity service = serviceRepository.findById(serviceId)
            .orElseThrow(() -> new RuntimeException("Service not found with ID: " + serviceId));

    // Option 1: Soft delete (recommended)
    service.setIsActive(false);
    serviceRepository.save(service);

    // Option 2: Hard delete (careful: will fail if there are FK constraints)
    // serviceRepository.delete(service);
}

}
