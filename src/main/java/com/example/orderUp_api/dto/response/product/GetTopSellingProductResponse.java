package com.example.orderUp_api.dto.response.product;

import com.example.orderUp_api.dto.common.RatingSummaryDto;
import com.example.orderUp_api.dto.sql.RatingSummaryQueryDto;
import com.example.orderUp_api.entity.sql.database.product.ProductEntity;
import com.example.orderUp_api.enums.ProductStatus;
import lombok.Data;

@Data
public class GetTopSellingProductResponse {
    private String id;
    //    private String code;
    private String name;
    private Long price;
    private String thumbnailUrl;
    private ProductStatus status;
    private RatingSummaryDto ratingSummary;

    public static GetTopSellingProductResponse fromProductEntity(ProductEntity entity, RatingSummaryQueryDto ratingSummaryQueryDto) {
        GetTopSellingProductResponse data = new GetTopSellingProductResponse();
        data.setId(entity.getId());
        data.setName(entity.getName());
        data.setPrice(entity.getPrice());
        data.setThumbnailUrl(entity.getImage().getThumbnailUrl());
        data.setStatus(entity.getStatus());
        data.setRatingSummary(RatingSummaryDto.fromRatingSummaryDto(ratingSummaryQueryDto));
        return data;
    }
}