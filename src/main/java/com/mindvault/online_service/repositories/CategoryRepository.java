package com.mindvault.online_service.repositories;

import com.mindvault.online_service.entities.Category;

import java.awt.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List findByNameContainingIgnoreCase(String name);
}