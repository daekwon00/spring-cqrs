package kr.or.study.springcqrs.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.user.dto.query.response.LoginHistoryResponse;
import kr.or.study.springcqrs.user.dto.query.response.UserProfileResponse;
import kr.or.study.springcqrs.user.dto.web.ChangePasswordWebRequest;
import kr.or.study.springcqrs.user.dto.web.UpdateProfileWebRequest;

import java.util.List;

@Tag(name = "사용자", description = "사용자 API")
public interface UserApi {

    @Operation(summary = "내 프로필 조회")
    ApiResponse<UserProfileResponse> getMyProfile();

    @Operation(summary = "프로필 수정")
    ApiResponse<UserProfileResponse> updateMyProfile(UpdateProfileWebRequest request);

    @Operation(summary = "비밀번호 변경")
    ApiResponse<Void> changePassword(ChangePasswordWebRequest request);

    @Operation(summary = "내 로그인 이력 조회")
    ApiResponse<List<LoginHistoryResponse>> getMyLoginHistory(int size);
}
