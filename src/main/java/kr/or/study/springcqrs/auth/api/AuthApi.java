package kr.or.study.springcqrs.auth.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import kr.or.study.springcqrs.auth.dto.query.response.LoginResponse;
import kr.or.study.springcqrs.auth.dto.query.response.TokenResponse;
import kr.or.study.springcqrs.auth.dto.query.response.UserInfoResponse;
import kr.or.study.springcqrs.auth.dto.web.LoginWebRequest;
import kr.or.study.springcqrs.auth.dto.web.RefreshWebRequest;
import kr.or.study.springcqrs.auth.dto.web.RegisterWebRequest;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;

@Tag(name = "인증", description = "인증 API")
public interface AuthApi {

    @Operation(summary = "로그인")
    ApiResponse<LoginResponse> login(LoginWebRequest request, HttpServletRequest httpRequest);

    @Operation(summary = "회원가입")
    ApiResponse<UserInfoResponse> register(RegisterWebRequest request);

    @Operation(summary = "토큰 갱신")
    ApiResponse<TokenResponse> refresh(RefreshWebRequest request);
}
