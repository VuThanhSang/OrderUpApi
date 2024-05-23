package com.example.orderUp_api.controller;

import com.example.orderUp_api.constant.StatusCode;
import com.example.orderUp_api.dto.request.product.CreateProductRequest;
import com.example.orderUp_api.dto.request.product.UpdateProductRequest;
import com.example.orderUp_api.dto.response.product.*;
import com.example.orderUp_api.enums.ProductSortType;
import com.example.orderUp_api.enums.ProductStatus;
import com.example.orderUp_api.model.ResponseAPI;
import com.example.orderUp_api.service.core.IProductService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
@Slf4j
@Validated
public class ProductController {
    private final IProductService productService;
    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseAPI<String>> createProduct(@ModelAttribute @Valid CreateProductRequest body) {
        try{
            productService.createProduct(body);
            ResponseAPI<String> res = ResponseAPI.<String>builder()
                    .timestamp(new Date())
                    .message("create successfully")
                    .build();
            return new ResponseEntity<>(res, StatusCode.CREATED);
        }
        catch (Exception e){
            ResponseAPI<String> res = ResponseAPI.<String>builder()
                    .timestamp(new Date())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, StatusCode.BAD_REQUEST);
        }
    }
    @PatchMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseAPI<String>> updateProduct(@PathVariable String id, @ModelAttribute @Valid UpdateProductRequest body) {
        try{
            body.setId(id);
            productService.updateProduct(body);
            ResponseAPI<String> res = ResponseAPI.<String>builder()
                    .timestamp(new Date())
                    .message("update successfully")
                    .build();
            return new ResponseEntity<>(res, StatusCode.CREATED);
        }
        catch (Exception e){
            ResponseAPI<String> res = ResponseAPI.<String>builder()
                    .timestamp(new Date())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, StatusCode.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseAPI<String>> deleteProduct(@PathVariable String id) {
        try{
            productService.deleteProduct(id);
            ResponseAPI<String> res = ResponseAPI.<String>builder()
                    .timestamp(new Date())
                    .message("delete successfully")
                    .build();
            return new ResponseEntity<>(res, StatusCode.OK);
        }
        catch (Exception e){
            ResponseAPI<String> res = ResponseAPI.<String>builder()
                    .timestamp(new Date())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, StatusCode.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/top-rated")
    public ResponseEntity<ResponseAPI<List<GetTopRatedProductResponse>>> getTopRatedProductQuantityOrder(@RequestParam int quantity) {
        try{
            List<GetTopRatedProductResponse> data =  productService.getTopRatedProductQuantityOrder(quantity);
            ResponseAPI<List<GetTopRatedProductResponse>> res = ResponseAPI.<List<GetTopRatedProductResponse>>builder()
                    .timestamp(new Date())
                    .message("get top rated product successfully")
                    .data(data)
                    .build();
            return new ResponseEntity<>(res, StatusCode.OK);
        }
        catch (Exception e){
            ResponseAPI<List<GetTopRatedProductResponse>> res = ResponseAPI.<List<GetTopRatedProductResponse>>builder()
                    .timestamp(new Date())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, StatusCode.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/top-selling")
    public ResponseEntity<ResponseAPI<List<GetTopSellingProductResponse>>> getTopSellingProductQuantityOrder(@RequestParam int quantity) {
        try{
            List<GetTopSellingProductResponse> data =  productService.getTopSellingProductQuantityOrder(quantity);
            ResponseAPI<List<GetTopSellingProductResponse>> res = ResponseAPI.<List<GetTopSellingProductResponse>>builder()
                    .timestamp(new Date())
                    .message("get top selling product successfully")
                    .data(data)
                    .build();
            return new ResponseEntity<>(res, StatusCode.OK);
        }
        catch (Exception e){
            ResponseAPI<List<GetTopSellingProductResponse>> res = ResponseAPI.<List<GetTopSellingProductResponse>>builder()
                    .timestamp(new Date())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, StatusCode.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseAPI<GetProductByIdResponse>> getProductDetailsById(@PathVariable String id) {
        try{
            GetProductByIdResponse data =  productService.getProductDetailsById(id);
            ResponseAPI<GetProductByIdResponse> res = ResponseAPI.<GetProductByIdResponse>builder()
                    .timestamp(new Date())
                    .message("get product details successfully")
                    .data(data)
                    .build();
            return new ResponseEntity<>(res, StatusCode.OK);
        }
        catch (Exception e){
            ResponseAPI<GetProductByIdResponse> res = ResponseAPI.<GetProductByIdResponse>builder()
                    .timestamp(new Date())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, StatusCode.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/view/{id}")
    public ResponseEntity<ResponseAPI<GetProductViewByIdResponse>> getProductViewById(@PathVariable String id) {
        try{
            GetProductViewByIdResponse data =  productService.getProductViewById(id);
            ResponseAPI<GetProductViewByIdResponse> res = ResponseAPI.<GetProductViewByIdResponse>builder()
                    .timestamp(new Date())
                    .message("get product view successfully")
                    .data(data)
                    .build();
            return new ResponseEntity<>(res, StatusCode.OK);
        }
        catch (Exception e){
            ResponseAPI<GetProductViewByIdResponse> res = ResponseAPI.<GetProductViewByIdResponse>builder()
                    .timestamp(new Date())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, StatusCode.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/category/{categoryId}")
    public ResponseEntity<ResponseAPI<GetProductsByCategoryIdResponse>> getProductsByCategoryId(
            @PathVariable("categoryId") String categoryId,
            @Parameter(name = "min_price", required = false, example = "1")
            @RequestParam(name = "min_price", required = false) @Min(value = 1, message = "min_price must be greater than 0") Long minPrice,

            @Parameter(name = "max_price", required = false, example = "1")
            @RequestParam(name = "max_price", required = false) @Min(value = 1, message = "max_price must be greater than 0") Long maxPrice,

            @Parameter(name = "min_star", required = false, example = "0")
            @RequestParam(name = "min_star", required = false, defaultValue = "0") @Min(value = 0, message = "Page must be greater than or equal 0") @Max(value = 5, message = "min_star must be lower than or equal 5") int minStar,

            @Parameter(name = "sort_type", required = false, example = "PRICE_DESC")
            @RequestParam(name = "sort_type", required = false) ProductSortType productSortType,

            @Parameter(name = "page", required = true, example = "1")
            @RequestParam("page") @Min(value = 1, message = "Page must be greater than 0") int page,
            @Parameter(name = "size", required = true, example = "10")
            @RequestParam("size") @Min(value = 1, message = "Size must be greater than 0") int size
    ) {
        try{
            GetProductsByCategoryIdResponse products = productService.getProductsByCategoryId(categoryId, minPrice, maxPrice, minStar, productSortType, page, size);

            ResponseAPI<GetProductsByCategoryIdResponse> res = ResponseAPI.<GetProductsByCategoryIdResponse>builder()
                    .timestamp(new Date())
                    .message("get product by category id successfully")
                    .data(products)
                    .build();
            return new ResponseEntity<>(res, StatusCode.OK);
        }
        catch (Exception e){
            ResponseAPI<GetProductsByCategoryIdResponse> res = ResponseAPI.<GetProductsByCategoryIdResponse>builder()
                    .timestamp(new Date())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, StatusCode.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/visible")
    public ResponseEntity<ResponseAPI<GetAllVisibleProductResponse>> getVisibleProductList(
            @Parameter(name = "key", description = "Key is name or description or id", required = false, example = "P0001")
            @RequestParam(name = "key", required = false, defaultValue = "") String key,

            @Parameter(name = "min_price", required = false, example = "1")
            @RequestParam(name = "min_price", required = false) @Min(value = 1, message = "min_price must be greater than 0") Long minPrice,

            @Parameter(name = "max_price", required = false, example = "1")
            @RequestParam(name = "max_price", required = false) @Min(value = 1, message = "max_price must be greater than 0") Long maxPrice,

            @Parameter(name = "min_star", required = false, example = "0")
            @RequestParam(name = "min_star", required = false, defaultValue = "0") @Min(value = 0, message = "Page must be greater than or equal 0") @Max(value = 5, message = "min_star must be lower than or equal 5") int minStar,

            @Parameter(name = "sort_type", required = false, example = "PRICE_DESC")
            @RequestParam(name = "sort_type", required = false) ProductSortType productSortType,

            @Parameter(name = "page", required = true, example = "1")
            @RequestParam("page") @Min(value = 1, message = "Page must be greater than 0") int page,
            @Parameter(name = "size", required = true, example = "10")
            @RequestParam("size") @Min(value = 1, message = "Size must be greater than 0") int size
    ){
        try{
            GetAllVisibleProductResponse products = productService.getVisibleProductList(minPrice, maxPrice, minStar, productSortType, page, size, key);

            ResponseAPI<GetAllVisibleProductResponse> res = ResponseAPI.<GetAllVisibleProductResponse>builder()
                    .timestamp(new Date())
                    .message("get visible product list successfully")
                    .data(products)
                    .build();
            return new ResponseEntity<>(res, StatusCode.OK);
        }
        catch (Exception e){
            ResponseAPI<GetAllVisibleProductResponse> res = ResponseAPI.<GetAllVisibleProductResponse>builder()
                    .timestamp(new Date())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, StatusCode.BAD_REQUEST);
        }

    }

    @GetMapping(path = "")
    public ResponseEntity<ResponseAPI<GetProductListResponse>> getProductList(
            @Parameter(name = "key", description = "Key is name or description or id", required = false, example = "P0001")
            @RequestParam(name = "key", required = false, defaultValue = "") String key,

            @Parameter(name = "page", required = true, example = "1")
            @RequestParam("page") @Min(value = 1, message = "Page must be greater than 0") int page,
            @Parameter(name = "size", required = true, example = "10")
            @RequestParam("size") @Min(value = 1, message = "Size must be greater than 0") int size,

            @Parameter(name = "category_id", required
            = false, example = "1")
            @RequestParam(name = "category_id", required = false) String categoryId,

            @Parameter(name = "product_status", required = false, example = "ACTIVE")
            @RequestParam(name = "product_status", required = false) ProductStatus productStatus
    ){
        try{
            GetProductListResponse products = productService.getProductList(key, page, size, categoryId, productStatus);

            ResponseAPI<GetProductListResponse> res = ResponseAPI.<GetProductListResponse>builder()
                    .timestamp(new Date())
                    .message("get product list successfully")
                    .data(products)
                    .build();
            return new ResponseEntity<>(res, StatusCode.OK);
        }
        catch (Exception e){
            ResponseAPI<GetProductListResponse> res = ResponseAPI.<GetProductListResponse>builder()
                    .timestamp(new Date())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, StatusCode.BAD_REQUEST);
        }
    }





}
