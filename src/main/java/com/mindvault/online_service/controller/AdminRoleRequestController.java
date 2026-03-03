package com.mindvault.online_service.controller;

import com.mindvault.online_service.entities.RoleRequest;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.security.CurrentUser;
import com.mindvault.online_service.service.RoleRequestService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/role-requests")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminRoleRequestController {

    private final RoleRequestService roleRequestService;

    @GetMapping
    public List<RoleRequest> getPendingRequests() {
        return roleRequestService.getPendingRequests();
    }

    @PostMapping("/{id}/approve")
    public RoleRequest approve(@PathVariable Long id, @CurrentUser User admin) {
        return roleRequestService.approveRequest(id, admin);
    }

    @PostMapping("/{id}/reject")
    public RoleRequest reject(@PathVariable Long id, @CurrentUser User admin) {
        return roleRequestService.rejectRequest(id, admin);
    }
}
