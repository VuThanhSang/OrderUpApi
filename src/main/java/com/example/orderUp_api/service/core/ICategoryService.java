package com.example.orderUp_api.service.core;

import com.example.orderUp_api.dto.request.category.CreateCategoryRequest;
import com.example.orderUp_api.dto.request.category.UpdateCategoryRequest;
import com.example.orderUp_api.dto.response.category.GetCategoryListResponse;
import com.example.orderUp_api.dto.response.category.GetVisibleCategoryListResponse;

import java.util.List;

public interface ICategoryService {
    void createCategory(CreateCategoryRequest request);
    void updateCategory(UpdateCategoryRequest request);
    List<GetCategoryListResponse> getCategoryList();
    List<GetVisibleCategoryListResponse> getVisibleCategoryList();
    void deleteCategory(String id);
}
