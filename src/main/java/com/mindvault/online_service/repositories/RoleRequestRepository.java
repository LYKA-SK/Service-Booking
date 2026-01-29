package com.mindvault.online_service.repositories;

import com.mindvault.online_service.entities.RoleRequest;
import com.mindvault.online_service.entities.RoleRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRequestRepository extends JpaRepository<RoleRequest, Long> {

    List<RoleRequest> findByStatus(RoleRequestStatus status);

    List<RoleRequest> findByUserId(Long userId);
}
