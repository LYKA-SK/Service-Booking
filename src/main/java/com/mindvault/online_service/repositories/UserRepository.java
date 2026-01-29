package com.mindvault.online_service.repositories;

import com.mindvault.online_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByfullName(String fullName);

    boolean existsByEmail(String email);
}