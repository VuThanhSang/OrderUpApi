package com.example.orderUp_api.dto.request.category;


import com.example.orderUp_api.enums.CategoryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static com.example.orderUp_api.constant.SwaggerConstant.*;

@Data
public class CreateCategoryRequest {
    @Schema()
    @NotNull

    private MultipartFile image;

    @Schema(example = CATEGORY_NAME_EX)
    @NotBlank
    private String name;

    @Schema(example = CATEGORY_STATUS_EX)
    @NotNull
    private CategoryStatus status;
}
