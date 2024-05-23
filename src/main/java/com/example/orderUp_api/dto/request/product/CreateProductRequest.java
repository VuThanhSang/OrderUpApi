package com.example.orderUp_api.dto.request.product;


import com.example.orderUp_api.dto.common.SizeDto;
import com.example.orderUp_api.dto.common.ToppingDto;
import com.example.orderUp_api.enums.ProductStatus;
import com.example.orderUp_api.enums.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.orderUp_api.constant.SwaggerConstant.*;

@Data
public class CreateProductRequest {
    @Schema(example = PRODUCT_NAME_EX)
    @NotBlank
    private String name;

    @Schema(example = PRODUCT_STATUS_EX)
    @NotNull
    private ProductStatus status;

    @Schema(example = PRODUCT_PRICE_EX)
    private Long price;

    @Schema()
    @NotNull
    private MultipartFile image;

    @Schema()
    private List<SizeDto> sizeList;

    @Schema(example = PRODUCT_DESCRIPTION_EX)
    @NotBlank
    private String description;

    @Schema()
    private List<ToppingDto> toppingList;

    @Schema(example = OBJECT_ID_EX)
    @NotNull
    private String categoryId;

    @Schema()
    @NotNull
    private ProductType productType;
}
