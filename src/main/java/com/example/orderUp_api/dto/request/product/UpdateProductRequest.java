package com.example.orderUp_api.dto.request.product;


import com.example.orderUp_api.dto.common.SizeDto;
import com.example.orderUp_api.dto.common.ToppingDto;
import com.example.orderUp_api.enums.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

import static com.example.orderUp_api.constant.SwaggerConstant.*;

@Data
public class UpdateProductRequest implements Serializable {
    @Schema()
    @NotBlank
    private String id;
    @Schema(example = PRODUCT_NAME_EX)
    @NotBlank
    private String name;

    @Schema( description = OPTIONAL_DES)
    private MultipartFile image;


    // example = PRODUCT_SIZE_EX,
    @Schema()
    @NotEmpty
    private List<SizeDto> sizeList;

    @Schema(example = PRODUCT_DESCRIPTION_EX)
    @NotBlank
    private String description;

    @Schema()
    private List<ToppingDto> toppingList;

    @Schema(example = OBJECT_ID_EX)
    @NotNull
    private String categoryId;

    @Schema(example = BOOLEAN_EX)
    @NotNull
    private boolean enabled;


    @Schema(example = PRODUCT_STATUS_EX)
    @NotNull
    private ProductStatus status;
}
