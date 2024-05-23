package com.example.orderUp_api.dto.response.product;


import com.example.orderUp_api.dto.common.RatingSummaryDto;
import com.example.orderUp_api.dto.sql.RatingSummaryQueryDto;
import com.example.orderUp_api.entity.sql.database.product.ProductEntity;
import com.example.orderUp_api.enums.ProductStatus;
import lombok.Data;

import java.util.List;

@Data
public class GetProductsByCategoryIdResponse {
    private Integer totalPage;
    private List<ProductCard> productList;

    @Data
    public static class ProductCard {

        private String id;
        private String name;
        private String description;
        private double price;
        private String thumbnailUrl;
        private ProductStatus status;
        private RatingSummaryDto ratingSummary;
        public static ProductCard fromProductEntity(ProductEntity entity, RatingSummaryQueryDto ratingSummaryQueryDto) {
            ProductCard data = new ProductCard();
            data.setId(entity.getId());
            data.setName(entity.getName());
            data.setDescription(entity.getDescription());
            data.setPrice(entity.getPrice());
            data.setThumbnailUrl(entity.getImage().getThumbnailUrl());
            data.setStatus(entity.getStatus());
            data.setRatingSummary(RatingSummaryDto.fromRatingSummaryDto(ratingSummaryQueryDto));
            return data;
        }
    }


//    public static List<GetProductsByCategoryIdResponse> fromProductEntityList(ProductEntity)
}
