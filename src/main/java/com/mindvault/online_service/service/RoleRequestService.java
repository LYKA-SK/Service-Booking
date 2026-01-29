package com.mindvault.online_service.service;

import com.mindvault.online_service.dtos.request.ProviderRoleRequest;
import com.mindvault.online_service.entities.RoleRequest;
import com.mindvault.online_service.entities.User;

import java.util.List;

public interface RoleRequestService {

    RoleRequest requestProviderRole(Long userId, ProviderRoleRequest request);

    List<RoleRequest> getPendingRequests();

    List<RoleRequest> getRequestsByUser(Long userId);

    RoleRequest approveRequest(Long requestId, User admin);

    RoleRequest rejectRequest(Long requestId, User admin);
}
