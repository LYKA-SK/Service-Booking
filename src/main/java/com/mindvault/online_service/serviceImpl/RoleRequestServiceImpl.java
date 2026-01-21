package com.mindvault.online_service.serviceImpl;

import com.mindvault.online_service.entities.*;
import com.mindvault.online_service.repositories.RoleRequestRepository;
import com.mindvault.online_service.repositories.UserRepository;
import com.mindvault.online_service.service.RoleRequestService;
import com.mindvault.online_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleRequestServiceImpl implements RoleRequestService {

    private final RoleRequestRepository roleRequestRepository;
    private final UserRepository userRepository;
    private final UserService userService; // for assigning role internally

    @Override
    public RoleRequest requestProviderRole(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean alreadyRequested = roleRequestRepository.findByUserId(userId).stream()
                .anyMatch(r -> r.getStatus() == RoleRequestStatus.PENDING);

        if (alreadyRequested) {
            throw new RuntimeException("You already have a pending provider request");
        }

        RoleRequest request = RoleRequest.builder()
                .user(user)
                .requestedRole(RoleEnum.PROVIDER)
                .status(RoleRequestStatus.PENDING)
                .build();

        return roleRequestRepository.save(request);
    }

    @Override
    public List<RoleRequest> getPendingRequests() {
        return roleRequestRepository.findByStatus(RoleRequestStatus.PENDING);
    }

    @Override
    public RoleRequest approveRequest(Long requestId, Long adminId) {
        RoleRequest request = roleRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getStatus() != RoleRequestStatus.PENDING) {
            throw new RuntimeException("Request already processed");
        }

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        // ✅ Use direct method args
        userService.assignRole(request.getUser().getId(), RoleEnum.PROVIDER);

        request.setStatus(RoleRequestStatus.APPROVED);
        request.setReviewedBy(admin);

        return roleRequestRepository.save(request);
    }

    @Override
    public RoleRequest rejectRequest(Long requestId, Long adminId) {
        RoleRequest request = roleRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getStatus() != RoleRequestStatus.PENDING) {
            throw new RuntimeException("Request already processed");
        }

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        request.setStatus(RoleRequestStatus.REJECTED);
        request.setReviewedBy(admin);

        return roleRequestRepository.save(request);
    }
}
