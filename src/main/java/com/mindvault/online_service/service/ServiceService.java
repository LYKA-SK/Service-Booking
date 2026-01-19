package com.mindvault.online_service.service;

import com.mindvault.online_service.dtos.request.ServiceRequest;
import com.mindvault.online_service.dtos.response.ServiceResponse;

import java.util.List;

public interface ServiceService {

    ServiceResponse createService(ServiceRequest request, Long userId);
    ServiceResponse updateService(Long serviceId, ServiceRequest request, Long userId);
    void deleteService(Long serviceId, Long userId);
    ServiceResponse getServiceById(Long serviceId);
    List<ServiceResponse> getAllServices();
    List<ServiceResponse> getServicesByProvider(Long providerId);
}
