package com.mindvault.online_service.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindvault.online_service.entities.RoleRequest;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.security.CurrentUser;
import com.mindvault.online_service.service.RoleRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/role-requests")
@RequiredArgsConstructor
public class RoleRequestController {

    private final RoleRequestService roleRequestService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public RoleRequest requestProvider(@CurrentUser User user) {
        return roleRequestService.requestProviderRole(user.getId());
    }
}
