package com.example.orderUp_api.service.core.Impl;

import com.example.orderUp_api.constant.CloudinaryConstant;
import com.example.orderUp_api.constant.ErrorConstant;
import com.example.orderUp_api.dto.request.category.CreateCategoryRequest;
import com.example.orderUp_api.dto.request.category.UpdateCategoryRequest;
import com.example.orderUp_api.dto.response.CloudinaryUploadResponse;
import com.example.orderUp_api.dto.response.category.GetCategoryListResponse;
import com.example.orderUp_api.dto.response.category.GetVisibleCategoryListResponse;
import com.example.orderUp_api.entity.sql.database.AlbumEntity;
import com.example.orderUp_api.entity.sql.database.CategoryEntity;
import com.example.orderUp_api.enums.AlbumType;
import com.example.orderUp_api.enums.CategoryStatus;
import com.example.orderUp_api.model.CustomException;
import com.example.orderUp_api.repository.database.CategoryRepository;
import com.example.orderUp_api.service.common.CloudinaryService;
import com.example.orderUp_api.service.common.ModelMapperService;
import com.example.orderUp_api.service.core.ICategoryService;
import com.example.orderUp_api.utils.ImageUtils;
import com.example.orderUp_api.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapperService modelMapperService;
    @Override
    public void createCategory(CreateCategoryRequest body) {
        if(!ImageUtils.isValidImageFile(body.getImage())) {
            throw new CustomException(ErrorConstant.IMAGE_INVALID);
        }
        String cgrName = body.getName();
        CategoryEntity existedCategory = categoryRepository.findByName(cgrName).orElse(null);
        if (existedCategory != null) {
            throw new CustomException(ErrorConstant.EXISTED_DATA, "Category already exists");
        }
        byte[] originalImage;
        try {
            originalImage = body.getImage().getBytes();
            byte[] newImage = ImageUtils.resizeImage(originalImage, 200, 200);

            CloudinaryUploadResponse fileUploaded = cloudinaryService.uploadFileToFolder(
                    CloudinaryConstant.CLASSROOM_PATH,
                    StringUtils.generateFileName(body.getName(), "classroom"),
                    newImage,
                    "image"
            );
            AlbumEntity image = AlbumEntity.builder()
                    .type(AlbumType.CATEGORY)
                    .cloudinaryImageId(fileUploaded.getPublicId())
                    .imageUrl(fileUploaded.getUrl())
                    .build();
            CategoryEntity category = CategoryEntity.builder()
                    .image(image)
                    .name(body.getName())
                    .status(body.getStatus())
                    .build();
            categoryRepository.save(category);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCategory(UpdateCategoryRequest body) {
        CategoryEntity category = categoryRepository.findById(body.getId())
                .orElseThrow(() -> new CustomException(ErrorConstant.NOT_FOUND, ErrorConstant.NOT_FOUND + body.getId()));
        if (body.getImage() != null) {
            try {
                byte[] originalImage = body.getImage().getBytes();
                byte[] newImage = ImageUtils.resizeImage(originalImage, 200, 200);

                CloudinaryUploadResponse fileUploaded = cloudinaryService.uploadFileToFolder(
                        CloudinaryConstant.CLASSROOM_PATH,
                        StringUtils.generateFileName(body.getName(), "classroom"),
                        newImage,
                        "image"
                );
                AlbumEntity image = AlbumEntity.builder()
                        .type(AlbumType.CATEGORY)
                        .cloudinaryImageId(fileUploaded.getPublicId())
                        .imageUrl(fileUploaded.getUrl())
                        .build();
                category.setImage(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        category.setName(body.getName());
        category.setStatus(body.getStatus());
        categoryRepository.save(category);
    }

    @Override
    public List<GetCategoryListResponse> getCategoryList() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return GetCategoryListResponse.fromCategoryEntityList(categoryEntityList);
    }

    @Override
    public List<GetVisibleCategoryListResponse> getVisibleCategoryList() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findByStatus(CategoryStatus.VISIBLE);
        return GetVisibleCategoryListResponse.fromCategoryEntityList(categoryEntityList);
    }

    @Override
    public void deleteCategory(String id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorConstant.NOT_FOUND, ErrorConstant.NOT_FOUND + id));
        categoryRepository.delete(category);

    }
}
