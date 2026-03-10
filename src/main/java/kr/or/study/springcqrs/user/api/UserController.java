package kr.or.study.springcqrs.user.api;

import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.config.security.SecurityUtils;
import kr.or.study.springcqrs.user.dto.command.request.UpdateProfileRequest;
import kr.or.study.springcqrs.user.dto.query.response.LoginHistoryResponse;
import kr.or.study.springcqrs.user.dto.query.response.UserProfileResponse;
import kr.or.study.springcqrs.user.dto.web.ChangePasswordWebRequest;
import kr.or.study.springcqrs.user.dto.web.UpdateProfileWebRequest;
import kr.or.study.springcqrs.user.service.command.UserCommandService;
import kr.or.study.springcqrs.user.service.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController implements UserApi {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    @Override
    @GetMapping("/me")
    public ApiResponse<UserProfileResponse> getMyProfile() {
        String userId = SecurityUtils.getCurrentUserId();
        return ApiResponse.ok(userQueryService.getUserProfile(userId));
    }

    @Override
    @PutMapping("/me")
    public ApiResponse<UserProfileResponse> updateMyProfile(
            @RequestBody @Validated UpdateProfileWebRequest request
    ) {
        String userId = SecurityUtils.getCurrentUserId();
        UpdateProfileRequest command = new UpdateProfileRequest(
                userId, request.name(), request.email(), request.phone());
        return ApiResponse.ok(userCommandService.updateProfile(userId, command));
    }

    @Override
    @PutMapping("/me/password")
    public ApiResponse<Void> changePassword(
            @RequestBody @Validated ChangePasswordWebRequest request
    ) {
        String userId = SecurityUtils.getCurrentUserId();
        userCommandService.changePassword(userId, request.currentPassword(), request.newPassword());
        return ApiResponse.ok(null, "비밀번호가 변경되었습니다.");
    }

    @Override
    @GetMapping("/me/login-history")
    public ApiResponse<List<LoginHistoryResponse>> getMyLoginHistory(
            @RequestParam(defaultValue = "10") int size
    ) {
        String userId = SecurityUtils.getCurrentUserId();
        return ApiResponse.ok(userQueryService.getLoginHistory(userId, size));
    }
}
