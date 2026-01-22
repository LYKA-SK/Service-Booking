package com.mindvault.online_service.service;

import com.mindvault.online_service.dtos.request.AvailabilityRequest;
import com.mindvault.online_service.dtos.response.AvailabilityResponse;
import java.util.List;

public interface AvailabilityService {
    AvailabilityResponse saveAvailability(AvailabilityRequest request, Long providerId);
    List<AvailabilityResponse> getAvailabilityByService(Long serviceId);
}