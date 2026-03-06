package kr.or.study.springcqrs.system.positionrole.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.system.positionrole.dto.query.response.PositionRoleResponse;
import kr.or.study.springcqrs.system.positionrole.dto.web.UpdatePositionRoleWebRequest;

import java.util.List;

@Tag(name = "직급-역할 매핑", description = "직급-역할 매핑 API")
public interface PositionRoleApi {

    @Operation(summary = "직급-역할 매핑 전체 조회")
    ApiResponse<List<PositionRoleResponse>> getAllPositionRoles();

    @Operation(summary = "직급별 역할 매핑 수정")
    ApiResponse<Void> updatePositionRoles(String positionId, UpdatePositionRoleWebRequest request);
}
