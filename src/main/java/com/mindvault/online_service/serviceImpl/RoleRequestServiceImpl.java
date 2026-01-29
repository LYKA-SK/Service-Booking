package com.mindvault.online_service.serviceImpl;

import com.mindvault.online_service.dtos.request.ProviderRoleRequest;
import com.mindvault.online_service.entities.*;
import com.mindvault.online_service.repositories.RoleRequestRepository;
import com.mindvault.online_service.repositories.UserRepository;
import com.mindvault.online_service.service.RoleRequestService;
import com.mindvault.online_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleRequestServiceImpl implements RoleRequestService {

    private final RoleRequestRepository roleRequestRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public RoleRequest requestProviderRole(Long userId, ProviderRoleRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() == RoleEnum.PROVIDER) {
            throw new RuntimeException("You are already a provider");
        }

        boolean alreadyRequested = roleRequestRepository.findByUserId(userId).stream()
                .anyMatch(r -> r.getStatus() == RoleRequestStatus.PENDING);

        if (alreadyRequested) {
            throw new RuntimeException("You already have a pending provider request");
        }

        RoleRequest roleRequest = RoleRequest.builder()
                .user(user)
                .requestedRole(RoleEnum.PROVIDER)
                .status(RoleRequestStatus.PENDING)
                .serviceType(request.getServiceType())
                .description(request.getDescription())
                .build();

        return roleRequestRepository.save(roleRequest);
    }

    @Override
    public List<RoleRequest> getRequestsByUser(Long userId) {
        return roleRequestRepository.findByUserId(userId);
    }

    @Override
    public List<RoleRequest> getPendingRequests() {
        return roleRequestRepository.findByStatus(RoleRequestStatus.PENDING);
    }

    @Override
    public RoleRequest approveRequest(Long requestId, User admin) {
        RoleRequest roleRequest = roleRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (roleRequest.getStatus() != RoleRequestStatus.PENDING) {
            throw new RuntimeException("Request already processed");
        }

        // ✅ Assign role to user
        userService.assignRole(roleRequest.getUser().getId(), RoleEnum.PROVIDER);

        roleRequest.setStatus(RoleRequestStatus.APPROVED);
        roleRequest.setReviewedBy(admin);
        roleRequest.setReviewedAt(LocalDateTime.now());

        return roleRequestRepository.save(roleRequest);
    }

    @Override
    public RoleRequest rejectRequest(Long requestId, User admin) {
        RoleRequest roleRequest = roleRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (roleRequest.getStatus() != RoleRequestStatus.PENDING) {
            throw new RuntimeException("Request already processed");
        }

        roleRequest.setStatus(RoleRequestStatus.REJECTED);
        roleRequest.setReviewedBy(admin);
        roleRequest.setReviewedAt(LocalDateTime.now());

        return roleRequestRepository.save(roleRequest);
    }
}
