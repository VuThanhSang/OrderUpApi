package com.example.orderUp_api.service.core;

import com.example.orderUp_api.dto.request.auth.ChangePasswordRequest;
import com.example.orderUp_api.dto.request.auth.LoginUserRequest;
import com.example.orderUp_api.dto.request.auth.RegisterUserRequest;
import com.example.orderUp_api.dto.response.auth.LoginResponse;
import com.example.orderUp_api.dto.response.auth.RefreshTokenResponse;
import com.example.orderUp_api.dto.response.auth.RegisterResponse;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface IUserAuthService {
    RegisterResponse registerUser(RegisterUserRequest body);
    LoginResponse loginUser(LoginUserRequest body);
    LoginResponse loginGoogleUser(OAuth2User oAuth2User);
    RefreshTokenResponse refreshToken(String refreshToken);

    void sendCodeToRegister(String email);

    void sendCodeForgotPassword(String email);

    void verifyCodeByEmail(String code, String email);

    @Transactional
    void changePasswordForgot(ChangePasswordRequest body);


}
