package com.mindvault.online_service.serviceImpl;

import com.mindvault.online_service.dtos.request.ServiceRequest;
import com.mindvault.online_service.dtos.response.ServiceResponse;
import com.mindvault.online_service.entities.RoleEnum;
import com.mindvault.online_service.entities.Service;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.repositories.ServiceRepository;
import com.mindvault.online_service.repositories.UserRepository;
import com.mindvault.online_service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    @Override
    public ServiceResponse createService(ServiceRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != RoleEnum.ADMIN && user.getRole() != RoleEnum.PROVIDER) {
            throw new AccessDeniedException("You are not allowed to create services");
        }

        Service service = Service.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .durationMinutes(request.getDurationMinutes())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .categoryId(request.getCategoryId())
                .providerId(user.getRole() == RoleEnum.PROVIDER ? userId : null)
                .build();

        Service saved = serviceRepository.save(service);
        return mapToResponse(saved);
    }

    @Override
    public ServiceResponse updateService(Long serviceId, ServiceRequest request, Long userId) {
        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() == RoleEnum.PROVIDER && !service.getProviderId().equals(userId)) {
            throw new AccessDeniedException("You can only update your own services");
        }

        service.setTitle(request.getTitle());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());
        service.setDurationMinutes(request.getDurationMinutes());
        service.setIsActive(request.getIsActive());
        service.setCategoryId(request.getCategoryId());

        return mapToResponse(serviceRepository.save(service));
    }

    @Override
    public void deleteService(Long serviceId, Long userId) {
        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() == RoleEnum.PROVIDER && !service.getProviderId().equals(userId)) {
            throw new AccessDeniedException("You can only delete your own services");
        }

        serviceRepository.delete(service);
    }

    @Override
    public ServiceResponse getServiceById(Long serviceId) {
        return mapToResponse(serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found")));
    }

    @Override
    public List<ServiceResponse> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceResponse> getServicesByProvider(Long providerId) {
        return serviceRepository.findByProviderId(providerId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ServiceResponse mapToResponse(Service service) {
        return ServiceResponse.builder()
                .id(service.getId())
                .title(service.getTitle())
                .description(service.getDescription())
                .price(service.getPrice())
                .durationMinutes(service.getDurationMinutes())
                .isActive(service.getIsActive())
                .categoryId(service.getCategoryId())
                .providerId(service.getProviderId())
                .build();
    }
}
