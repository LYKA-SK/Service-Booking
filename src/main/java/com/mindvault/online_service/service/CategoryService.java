package com.mindvault.online_service.service;

// FIX: Make sure this import is exactly like this
import com.mindvault.online_service.dtos.request.CategoryRequest; 
import com.mindvault.online_service.dtos.response.CategoryResponse;
import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Integer id);
    CategoryResponse createCategory(CategoryRequest request, Integer adminId);
    CategoryResponse updateCategory(Integer id, CategoryRequest request);
    void deleteCategory(Integer id);
}