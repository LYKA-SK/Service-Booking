package com.mindvault.online_service.controller;

import com.mindvault.online_service.dtos.request.ProviderRoleRequest;
import com.mindvault.online_service.entities.RoleRequest;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.security.CurrentUser;
import com.mindvault.online_service.service.RoleRequestService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role-requests")
@RequiredArgsConstructor
public class RoleRequestController {

    private final RoleRequestService roleRequestService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public RoleRequest requestProvider(@CurrentUser User user,
                                       @RequestBody ProviderRoleRequest request) {
        return roleRequestService.requestProviderRole(user.getId(), request);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<RoleRequest> myRequests(@CurrentUser User user) {
        return roleRequestService.getRequestsByUser(user.getId());
    }
}
