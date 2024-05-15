package com.example.orderUp_api.dto.common;

import com.example.orderUp_api.enums.ProductSize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static com.example.orderUp_api.constant.SwaggerConstant.*;

@Data
public class SizeDto {
    @Schema(example = PRODUCT_SIZE_EX)
    @NotNull
    private ProductSize size;

    @Schema(example = PRODUCT_PRICE_EX)
    @Min(PRODUCT_PRICE_MIN)
    private Long price;
}
