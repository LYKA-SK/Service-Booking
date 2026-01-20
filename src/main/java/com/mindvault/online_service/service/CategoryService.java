package com.mindvault.online_service.service;

import com.mindvault.online_service.dtos.request.CategoryRequest;
import com.mindvault.online_service.dtos.response.CategoryResponse;
import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);
    void deleteCategory(Long id);
}