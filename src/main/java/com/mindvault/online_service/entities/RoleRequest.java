package com.mindvault.online_service.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "role_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user who requested the role
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Requested role (usually PROVIDER)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum requestedRole;

    // Status: PENDING / APPROVED / REJECTED
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleRequestStatus status;

    // Optional: Which admin reviewed it
    @ManyToOne
    @JoinColumn(name = "reviewed_by")
    private User reviewedBy;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime reviewedAt;
}
