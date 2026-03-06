package kr.or.study.springcqrs.system.menu.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.system.menu.dto.query.response.MyMenusResponse;

@Tag(name = "내 메뉴", description = "사용자 메뉴 API")
public interface MyMenuApi {

    @Operation(summary = "내 메뉴 조회")
    ApiResponse<MyMenusResponse> getMyMenus();
}
