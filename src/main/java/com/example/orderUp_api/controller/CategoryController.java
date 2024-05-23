package com.example.orderUp_api.controller;

import com.example.orderUp_api.constant.StatusCode;
import com.example.orderUp_api.dto.request.auth.RegisterUserRequest;
import com.example.orderUp_api.dto.request.category.CreateCategoryRequest;
import com.example.orderUp_api.dto.request.category.UpdateCategoryRequest;
import com.example.orderUp_api.dto.response.auth.RegisterResponse;
import com.example.orderUp_api.dto.response.category.GetCategoryListResponse;
import com.example.orderUp_api.dto.response.category.GetVisibleCategoryListResponse;
import com.example.orderUp_api.model.ResponseAPI;
import com.example.orderUp_api.service.core.ICategoryService;
import com.example.orderUp_api.utils.CookieUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.example.orderUp_api.constant.RouterConstant.USER_AUTH_BASE_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
@Slf4j
public class CategoryController {
    private final ICategoryService categoryService;

    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseAPI<String>> createCategory(@ModelAttribute @Valid CreateCategoryRequest body) {
        try{
            categoryService.createCategory(body);
            ResponseAPI<String> res = ResponseAPI.<String>builder()
                    .timestamp(new Date())
                    .message("Register successfully")
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
    public ResponseEntity<ResponseAPI<String>> updateCategory(@PathVariable String id, @ModelAttribute @Valid UpdateCategoryRequest body) {
        try{
            body.setId(id);

            categoryService.updateCategory( body);
            ResponseAPI<String> res = ResponseAPI.<String>builder()
                    .timestamp(new Date())
                    .message("Update successfully")
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

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseAPI<String>> deleteCategory(@PathVariable String id) {
        try{
            categoryService.deleteCategory(id);
            ResponseAPI<String> res = ResponseAPI.<String>builder()
                    .timestamp(new Date())
                    .message("Delete successfully")
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

    @GetMapping(path = "")
    public ResponseEntity<ResponseAPI<  List<GetCategoryListResponse>>> getCategory(@PathVariable String id) {
        try{
            List<GetCategoryListResponse> data = categoryService.getCategoryList();
            ResponseAPI<  List<GetCategoryListResponse>> res = ResponseAPI.<  List<GetCategoryListResponse>>builder()
                    .timestamp(new Date())
                    .message("Get successfully")
                    .data(data)
                    .build();
            return new ResponseEntity<>(res, StatusCode.OK);
        }
        catch (Exception e){
            ResponseAPI<  List<GetCategoryListResponse>> res = ResponseAPI.<  List<GetCategoryListResponse>>builder()
                    .timestamp(new Date())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, StatusCode.BAD_REQUEST);
        }

    }
    @GetMapping(path = "/visible")
    public ResponseEntity<ResponseAPI<  List<GetVisibleCategoryListResponse>>> getVisibleCategory(@PathVariable String id) {
        try{
            List<GetVisibleCategoryListResponse> data = categoryService.getVisibleCategoryList();
            ResponseAPI<  List<GetVisibleCategoryListResponse>> res = ResponseAPI.<  List<GetVisibleCategoryListResponse>>builder()
                    .timestamp(new Date())
                    .message("Get successfully")
                    .data(data)
                    .build();
            return new ResponseEntity<>(res, StatusCode.OK);
        }
        catch (Exception e){
            ResponseAPI<  List<GetVisibleCategoryListResponse>> res = ResponseAPI.<  List<GetVisibleCategoryListResponse>>builder()
                    .timestamp(new Date())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, StatusCode.BAD_REQUEST);
        }

    }
}
