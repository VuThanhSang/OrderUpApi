package com.example.orderUp_api.service.core.Impl;

import com.example.orderUp_api.constant.CloudinaryConstant;
import com.example.orderUp_api.constant.ErrorConstant;
import com.example.orderUp_api.dto.common.RatingSummaryDto;
import com.example.orderUp_api.dto.request.product.CreateProductRequest;
import com.example.orderUp_api.dto.request.product.UpdateProductRequest;
import com.example.orderUp_api.dto.response.CloudinaryUploadResponse;
import com.example.orderUp_api.dto.response.product.*;
import com.example.orderUp_api.dto.sql.RatingSummaryQueryDto;
import com.example.orderUp_api.entity.sql.database.AlbumEntity;
import com.example.orderUp_api.entity.sql.database.CategoryEntity;
import com.example.orderUp_api.entity.sql.database.product.ProductEntity;
import com.example.orderUp_api.entity.sql.database.product.SizeEntity;
import com.example.orderUp_api.entity.sql.database.product.ToppingEntity;
import com.example.orderUp_api.enums.AlbumType;
import com.example.orderUp_api.enums.ProductSortType;
import com.example.orderUp_api.enums.ProductStatus;
import com.example.orderUp_api.enums.ProductType;
import com.example.orderUp_api.model.CustomException;
import com.example.orderUp_api.repository.database.AlbumRepository;
import com.example.orderUp_api.repository.database.CategoryRepository;
import com.example.orderUp_api.repository.database.product.ProductRepository;
import com.example.orderUp_api.repository.database.review.ProductReviewRepository;
import com.example.orderUp_api.service.common.CloudinaryService;
import com.example.orderUp_api.service.common.ModelMapperService;
import com.example.orderUp_api.service.core.IProductService;
import com.example.orderUp_api.utils.ImageUtils;
import com.example.orderUp_api.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService {
    private final CloudinaryService cloudinaryService;
    private final ProductRepository productRepository;
    private final ModelMapperService modelMapperService;
    private final CategoryRepository categoryRepository;
    private final ProductReviewRepository productReviewRepository;
    private final AlbumRepository albumRepository;
    public static long getMinPrice(List<SizeEntity> sizeList) {
        long min = sizeList.get(0).getPrice();
        for (SizeEntity item : sizeList) {
            if (min > item.getPrice()) {
                min = item.getPrice();
            }
        }
        return min;
    }
    @Override
    public void createProduct(CreateProductRequest body) {
        if (!ImageUtils.isValidImageFile(body.getImage())) {
            throw new CustomException(ErrorConstant.IMAGE_INVALID);
        }
        if ((body.getProductType() == ProductType.BEVERAGE && (body.getSizeList() == null || body.getPrice() != null))) {
            throw new CustomException(ErrorConstant.DATA_SEND_INVALID, "Beverage need size and not price");
        } else if (body.getProductType() == ProductType.CAKE) {
            if (body.getToppingList() != null || body.getSizeList() != null || body.getPrice() == null) {
                throw new CustomException(ErrorConstant.DATA_SEND_INVALID, "Cakes do not need toppings or size, and need a price");
            }
        }
        if (productRepository.findByName(body.getName()).orElse(null) != null) {
            throw new CustomException(ErrorConstant.EXISTED_DATA, "Product named \"" + body.getName() + "\" already exists");
        }

        ProductEntity product = modelMapperService.mapClass(body, ProductEntity.class);
        if (body.getToppingList() != null) {
            List<ToppingEntity> toppingList = ToppingEntity.fromToppingDtoList(body.getToppingList(), product);
            product.setToppingList(toppingList);
        }
        if (body.getSizeList() != null) {
            List<SizeEntity> sizeList = SizeEntity.fromToppingDtoList(body.getSizeList(), product);
            product.setSizeList(sizeList);
        }
        product.setType(body.getProductType());
        product.setName(body.getName());
        product.setDescription(body.getDescription());
        product.setStatus(body.getStatus());

        if (body.getProductType() == ProductType.CAKE) {
            product.setPrice(body.getPrice());
        } else if (body.getProductType() == ProductType.BEVERAGE) {
            product.setPrice(getMinPrice(product.getSizeList()));
        }
        byte[] originalImage = new byte[0];
        try {
            originalImage = body.getImage().getBytes();
            byte[] newImage = ImageUtils.resizeImage(originalImage, 200, 200);

            CategoryEntity categoryEntity = categoryRepository.findById(body.getCategoryId())
                    .orElseThrow(() -> new CustomException(ErrorConstant.NOT_FOUND, ErrorConstant.NOT_FOUND + body.getCategoryId()));

            product.setCategory(categoryEntity);
            CloudinaryUploadResponse imageUploaded = cloudinaryService.uploadFileToFolder(
                    CloudinaryConstant.CLASSROOM_PATH,
                    StringUtils.generateFileName(body.getName(), "classroom"),
                    newImage,
                    "image"
            );

            AlbumEntity productImage = AlbumEntity.builder()
                    .imageUrl(imageUploaded.getUrl())
                    .type(AlbumType.PRODUCT)
                    .cloudinaryImageId(imageUploaded.getPublicId())
                    .thumbnailUrl(cloudinaryService.getThumbnailUrl(imageUploaded.getPublicId()))
                    .build();
            product.setImage(productImage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ProductEntity dataSaved = productRepository.save(product);
//        productSearchService.createProduct(dataSaved);
    }

    @Override
    public void updateProduct(UpdateProductRequest body) {
        ProductEntity product = productRepository.findById(body.getId())
                .orElseThrow(() -> new CustomException(ErrorConstant.NOT_FOUND, ErrorConstant.NOT_FOUND + body.getId()));

        modelMapperService.map(body, product);

        if (body.getImage() != null) {
            try {
//                if(product.getImage().getCloudinaryImageId() != null) {
//                    cloudinaryService.deleteImage(product.getImage().getCloudinaryImageId());
//                }

                byte[] originalImage = body.getImage().getBytes();

                byte[] newImage = ImageUtils.resizeImage(originalImage, 200, 200);
                CloudinaryUploadResponse fileUploaded = cloudinaryService.uploadFileToFolder(
                        CloudinaryConstant.CLASSROOM_PATH,
                        StringUtils.generateFileName(body.getName(), "classroom"),
                        newImage,
                        "image"
                );

                AlbumEntity productImage = AlbumEntity.builder()
                        .type(AlbumType.PRODUCT)
                        .imageUrl(fileUploaded.getUrl())
                        .cloudinaryImageId(fileUploaded.getPublicId())
                        .thumbnailUrl(cloudinaryService.getThumbnailUrl(fileUploaded.getPublicId()))
                        .build();
                product.setImage(productImage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deleteProduct(String id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorConstant.NOT_FOUND, ErrorConstant.NOT_FOUND + id));

        if (productRepository.countOrderItem(id) == 0) {
            productRepository.delete(product);
            if (product.getImage().getCloudinaryImageId() == null) {
                albumRepository.delete(product.getImage());
            }
//            productSearchService.deleteProduct(id);
        } else {
            throw new CustomException(ErrorConstant.CANT_DELETE);
        }
    }

    @Override
    public List<GetTopRatedProductResponse> getTopRatedProductQuantityOrder(int quantity) {
        List<GetTopRatedProductResponse> data = new ArrayList<>();

        List<ProductEntity> productEntityList = productRepository.getTopRatingProduct(quantity);
        for (ProductEntity entity : productEntityList) {
            RatingSummaryQueryDto ratingSummaryQueryDto = productReviewRepository.getRatingSummary(entity.getId());
            data.add(GetTopRatedProductResponse.fromProductEntity(entity, ratingSummaryQueryDto));
        }

        return data;
    }

    @Override
    public List<GetTopSellingProductResponse> getTopSellingProductQuantityOrder(int quantity) {
        List<GetTopSellingProductResponse> data = new ArrayList<>();

        List<ProductEntity> productEntityList = productRepository.getTopProductBySoldQuantity(quantity);
        for (ProductEntity entity : productEntityList) {
            RatingSummaryQueryDto ratingSummaryQueryDto = productReviewRepository.getRatingSummary(entity.getId());
            data.add(GetTopSellingProductResponse.fromProductEntity(entity, ratingSummaryQueryDto));
        }

        return data;
    }

    @Override
    public GetProductByIdResponse getProductDetailsById(String id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorConstant.NOT_FOUND, ErrorConstant.NOT_FOUND + id));
        GetProductByIdResponse result = modelMapperService.mapClass(product, GetProductByIdResponse.class);
        result.setImageUrl(product.getImage().getImageUrl());
        result.setCategoryId(product.getCategory().getId());
        return result;
    }

    @Override
    public GetProductViewByIdResponse getProductViewById(String id) {
        ProductEntity product = productRepository.findByIdAndStatusNot(id, ProductStatus.HIDDEN)
                .orElseThrow(() -> new CustomException(ErrorConstant.NOT_FOUND, ErrorConstant.NOT_FOUND + id));
        GetProductViewByIdResponse data = modelMapperService.mapClass(product, GetProductViewByIdResponse.class);

        data.setImageUrl(product.getImage().getImageUrl());

        RatingSummaryQueryDto ratingSummaryQueryDto = productReviewRepository.getRatingSummary(product.getId());
        data.setRatingSummary(RatingSummaryDto.fromRatingSummaryDto(ratingSummaryQueryDto));

        return data;
    }

    @Override
    public GetProductsByCategoryIdResponse getProductsByCategoryId(String categoryId, Long minPrice, Long maxPrice, int minStar, ProductSortType productSortType, int page, int size) {
        GetProductsByCategoryIdResponse data = new GetProductsByCategoryIdResponse();
        List<GetProductsByCategoryIdResponse.ProductCard> productList = new ArrayList<>();

        data.setProductList(productList);
        // TODO: nên check category not hidden
        Page<ProductEntity> productPage = null;

        Pageable pageable = PageRequest.of(page - 1, size);
        if (minPrice != null && maxPrice != null) {
            if (productSortType == ProductSortType.PRICE_DESC) {
                pageable = PageRequest.of(page - 1, size, Sort.by("price").descending());
            } else if (productSortType == ProductSortType.PRICE_ASC) {
                pageable = PageRequest.of(page - 1, size, Sort.by("price").ascending());
            }
            productPage = productRepository.getProductByCategoryIdAndFilter(categoryId, minPrice, maxPrice, minStar, pageable);

        } else {
            productPage = productRepository.findByCategory_IdAndStatusNot(categoryId, ProductStatus.HIDDEN, PageRequest.of(page - 1, size));
        }
        data.setTotalPage(productPage.getTotalPages());

        List<ProductEntity> productEntityList = productPage.getContent();
        for (ProductEntity entity : productEntityList) {
            RatingSummaryQueryDto ratingSummary = productReviewRepository.getRatingSummary(entity.getId());
            productList.add(GetProductsByCategoryIdResponse.ProductCard.fromProductEntity(entity, ratingSummary));
        }
        return data;
    }

    @Override
    public GetAllVisibleProductResponse getVisibleProductList(Long minPrice, Long maxPrice, int minStar, ProductSortType productSortType, int page, int size, String key) {
        return null;
    }

    @Override
    public GetProductListResponse getProductList(String key, int page, int size, String categoryId, ProductStatus productStatus) {
        return null;
    }
}
