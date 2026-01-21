package com.mindvault.online_service.service;

import com.mindvault.online_service.entities.RoleRequest;

import java.util.List;

public interface RoleRequestService {

    RoleRequest requestProviderRole(Long userId);

    List<RoleRequest> getPendingRequests();

    RoleRequest approveRequest(Long requestId, Long adminId);

    RoleRequest rejectRequest(Long requestId, Long adminId);
}
