package com.mindvault.online_service.service;

import com.mindvault.online_service.dtos.request.ServiceRequest;
import com.mindvault.online_service.dtos.response.ServiceResponse;
import com.mindvault.online_service.entities.User;

import java.util.List;

public interface ServiceService {
    ServiceResponse createService(ServiceRequest request, User creator); // Admin or Provider
    List<ServiceResponse> getAllServices(); // Users can view all active services
    ServiceResponse getServiceById(Long id);
    ServiceResponse updateService(Long serviceId, ServiceRequest request);
    void deleteService(Long serviceId);

    

   
}
