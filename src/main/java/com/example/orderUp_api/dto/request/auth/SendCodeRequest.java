package com.example.orderUp_api.dto.request.auth;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.example.orderUp_api.constant.SwaggerConstant.EMAIL_EX;

@Data
public class SendCodeRequest {
    @Schema(example = EMAIL_EX)
    @Email
    @NotBlank
    private String email;

}
