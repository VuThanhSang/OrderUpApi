package com.example.orderUp_api.dto.response.product;


import com.example.orderUp_api.dto.common.SizeDto;
import com.example.orderUp_api.dto.common.ToppingDto;
import com.example.orderUp_api.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class GetProductByIdResponse {
    private String id;
    private String name;
    private List<SizeDto> sizeList;
    private String description;
    private List<ToppingDto> toppingList;
    private String categoryId;
    private ProductStatus status;
    private String imageUrl;

}