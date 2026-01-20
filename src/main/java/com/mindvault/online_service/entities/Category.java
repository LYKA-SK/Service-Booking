package com.mindvault.online_service.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //

    @Column(nullable = false)
    private String name; //

    @Column(columnDefinition = "TEXT")
    private String description; //

    @Column(name = "user_id", nullable = false)
    private Long userId; // The admin who created it

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; //

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}