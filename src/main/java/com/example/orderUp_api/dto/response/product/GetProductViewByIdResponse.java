package com.example.orderUp_api.dto.response.product;

import com.example.orderUp_api.dto.common.RatingSummaryDto;
import com.example.orderUp_api.dto.common.SizeDto;
import com.example.orderUp_api.dto.common.ToppingDto;
import com.example.orderUp_api.enums.ProductStatus;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
public class GetProductViewByIdResponse {
    @Id
    private String id;
    private String name;
    private String imageUrl;
    private List<SizeDto> sizeList;
    private String description;
    private List<ToppingDto> toppingList;
    private ProductStatus status;
    private RatingSummaryDto ratingSummary;
}
