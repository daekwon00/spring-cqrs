package kr.or.study.springcqrs.admin.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.admin.user.dto.query.response.AdminUserResponse;
import kr.or.study.springcqrs.admin.user.dto.web.CreateUserWebRequest;
import kr.or.study.springcqrs.admin.user.dto.web.UpdateUserWebRequest;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.common.dto.response.PageResponse;

@Tag(name = "관리자 사용자 관리", description = "관리자 사용자 관리 API")
public interface AdminUserApi {

    @Operation(summary = "사용자 목록 조회")
    ApiResponse<PageResponse<AdminUserResponse>> getUserList(int page, int size, String search);

    @Operation(summary = "사용자 상세 조회")
    ApiResponse<AdminUserResponse> getUserById(String userId);

    @Operation(summary = "사용자 생성")
    ApiResponse<AdminUserResponse> createUser(CreateUserWebRequest request);

    @Operation(summary = "사용자 수정")
    ApiResponse<AdminUserResponse> updateUser(String userId, UpdateUserWebRequest request);

    @Operation(summary = "사용자 활성/비활성 토글")
    ApiResponse<AdminUserResponse> toggleActive(String userId);
}
