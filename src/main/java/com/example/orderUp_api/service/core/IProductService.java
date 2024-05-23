package com.example.orderUp_api.service.core;

import com.example.orderUp_api.dto.request.product.CreateProductRequest;
import com.example.orderUp_api.dto.request.product.UpdateProductRequest;
import com.example.orderUp_api.dto.response.product.*;
import com.example.orderUp_api.enums.ProductSortType;
import com.example.orderUp_api.enums.ProductStatus;

import java.util.List;

public interface IProductService {
    void createProduct(CreateProductRequest request);
    void updateProduct(UpdateProductRequest request);
    void deleteProduct(String id);
    List<GetTopRatedProductResponse> getTopRatedProductQuantityOrder(int quantity);
    List<GetTopSellingProductResponse> getTopSellingProductQuantityOrder(int quantity);
    GetProductByIdResponse getProductDetailsById(String id);
    GetProductViewByIdResponse getProductViewById(String id);
    GetProductsByCategoryIdResponse getProductsByCategoryId(String categoryId, Long minPrice, Long maxPrice, int minStar, ProductSortType productSortType, int page, int size);
    GetAllVisibleProductResponse getVisibleProductList(Long minPrice, Long maxPrice, int minStar, ProductSortType productSortType, int page, int size, String key);
    GetProductListResponse getProductList(String key, int page, int size, String categoryId, ProductStatus productStatus);
}
