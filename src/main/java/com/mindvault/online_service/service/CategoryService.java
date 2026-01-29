package com.mindvault.online_service.service;

import com.mindvault.online_service.dtos.request.CategoryRequest;
import com.mindvault.online_service.dtos.response.CategoryResponse;
import com.mindvault.online_service.entities.User;
import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    CategoryResponse createCategory(CategoryRequest request, User admin);
    CategoryResponse updateCategory(Long id, CategoryRequest request);
    void deleteCategory(Long id);
}