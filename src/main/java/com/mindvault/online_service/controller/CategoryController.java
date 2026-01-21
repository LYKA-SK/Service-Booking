package com.mindvault.online_service.controller;

import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.dtos.request.CategoryRequest;
import com.mindvault.online_service.dtos.response.CategoryResponse;
import com.mindvault.online_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping
    // Change 'admin' to 'ROLE_admin' to match your User entity implementation
    @PreAuthorize("hasAuthority('ROLE_admin')") 
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest req, Authentication auth) {
        // "Catch" the User entity directly from the authentication object
        User user = (User) auth.getPrincipal();
        
        // Use user.getId() to satisfy the user_id column in your DB schema
        return ResponseEntity.ok(categoryService.createCategory(req, user.getId().intValue()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_admin')")
    public ResponseEntity<CategoryResponse> update(@PathVariable Integer id, @RequestBody CategoryRequest req) {
        return ResponseEntity.ok(categoryService.updateCategory(id, req));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_admin')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}