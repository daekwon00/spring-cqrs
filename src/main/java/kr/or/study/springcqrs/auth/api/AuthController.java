package kr.or.study.springcqrs.auth.api;

import jakarta.servlet.http.HttpServletRequest;
import kr.or.study.springcqrs.auth.dto.query.response.LoginResponse;
import kr.or.study.springcqrs.auth.dto.query.response.TokenResponse;
import kr.or.study.springcqrs.auth.dto.query.response.UserInfoResponse;
import kr.or.study.springcqrs.auth.dto.web.LoginWebRequest;
import kr.or.study.springcqrs.auth.dto.web.RefreshWebRequest;
import kr.or.study.springcqrs.auth.dto.web.RegisterWebRequest;
import kr.or.study.springcqrs.auth.service.command.AuthCommandService;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.common.exception.BusinessException;
import kr.or.study.springcqrs.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthApi {

    private final AuthCommandService authCommandService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @RequestBody @Validated LoginWebRequest request,
            HttpServletRequest httpRequest
    ) {
        String loginIp = httpRequest.getRemoteAddr();
        LoginResponse response = authCommandService.login(request, loginIp);
        return ApiResponse.ok(response);
    }

    @Override
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserInfoResponse> register(
            @RequestBody @Validated RegisterWebRequest request
    ) {
        UserInfoResponse response = authCommandService.register(request);
        return ApiResponse.ok(response);
    }

    @Override
    @PostMapping("/refresh")
    public ApiResponse<TokenResponse> refresh(
            @RequestBody @Validated RefreshWebRequest request
    ) {
        if (!jwtTokenProvider.validateToken(request.refreshToken())) {
            throw new BusinessException("유효하지 않은 리프레시 토큰입니다.", HttpStatus.UNAUTHORIZED);
        }

        String userId = jwtTokenProvider.getUserId(request.refreshToken());
        List<String> roles = jwtTokenProvider.getRoles(request.refreshToken());
        String newAccessToken = jwtTokenProvider.createAccessToken(userId, roles != null ? roles : List.of());

        return ApiResponse.ok(new TokenResponse(newAccessToken));
    }
}
