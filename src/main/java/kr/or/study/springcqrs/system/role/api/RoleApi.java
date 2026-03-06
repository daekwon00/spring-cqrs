package kr.or.study.springcqrs.system.role.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.system.role.dto.query.response.RoleResponse;
import kr.or.study.springcqrs.system.role.dto.web.CreateRoleWebRequest;
import kr.or.study.springcqrs.system.role.dto.web.UpdateRoleWebRequest;

import java.util.List;

@Tag(name = "역할 관리", description = "역할 관리 API")
public interface RoleApi {

    @Operation(summary = "역할 목록 조회")
    ApiResponse<List<RoleResponse>> getRoleList();

    @Operation(summary = "역할 생성")
    ApiResponse<RoleResponse> createRole(CreateRoleWebRequest request);

    @Operation(summary = "역할 수정")
    ApiResponse<RoleResponse> updateRole(String roleId, UpdateRoleWebRequest request);

    @Operation(summary = "역할 삭제")
    ApiResponse<Void> deleteRole(String roleId);
}
