package com.example.orderUp_api.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import static com.example.orderUp_api.constant.SwaggerConstant.*;
@Data
@Builder
public class RegisterUserRequest {
    @Schema(example = EMAIL_EX)
    @Email
    @NotBlank
    private String email;

    @Schema(example = PASSWORD_EX)
    @NotBlank
    @Size(min = PASSWORD_LENGTH_MIN, max = PASSWORD_LENGTH_MAX)
    private String password;

    @Schema(example = USERNAME_EX)
    @NotBlank
    private String username;

    @NotBlank
    private String fullname;

    @NotBlank
    private String role;


    @Schema(example = PHONE_NUMBER_EX)
    @Pattern(regexp = PHONE_NUMBER_REGEX)
    private String phoneNumber;


}
