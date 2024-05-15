package com.example.orderUp_api.dto.common;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.example.orderUp_api.constant.SwaggerConstant.*;

@Data
public class ToppingDto {
    @Schema(example = TOPPING_NAME_EX)
    @NotBlank
    private String name;

    @Schema(example = TOPPING_PRICE_EX)
    @Min(TOPPING_PRICE_MIN)
    private Long price;
}
