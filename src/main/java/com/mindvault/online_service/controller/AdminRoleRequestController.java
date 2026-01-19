package com.mindvault.online_service.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindvault.online_service.entities.RoleRequest;
import com.mindvault.online_service.service.RoleRequestService;

import lombok.RequiredArgsConstructor;

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
    public RoleRequest approve(@PathVariable Long id, @RequestHeader("adminId") Long adminId) {
        return roleRequestService.approveRequest(id, adminId);
    }

    @PostMapping("/{id}/reject")
    public RoleRequest reject(@PathVariable Long id, @RequestHeader("adminId") Long adminId) {
        return roleRequestService.rejectRequest(id, adminId);
    }
}
