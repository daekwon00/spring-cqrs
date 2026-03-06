package kr.or.study.springcqrs.system.menurole.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.system.menurole.dto.query.response.MenuRoleResponse;
import kr.or.study.springcqrs.system.menurole.dto.web.UpdateMenuRoleWebRequest;

import java.util.List;

@Tag(name = "메뉴-역할 매핑", description = "메뉴-역할 매핑 API")
public interface MenuRoleApi {

    @Operation(summary = "메뉴-역할 매핑 전체 조회")
    ApiResponse<List<MenuRoleResponse>> getAllMenuRoles();

    @Operation(summary = "역할별 메뉴 매핑 수정")
    ApiResponse<Void> updateMenuRoles(String roleId, UpdateMenuRoleWebRequest request);
}
