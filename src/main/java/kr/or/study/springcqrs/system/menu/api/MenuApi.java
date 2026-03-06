package kr.or.study.springcqrs.system.menu.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.system.menu.dto.query.response.MenuResponse;
import kr.or.study.springcqrs.system.menu.dto.web.CreateMenuWebRequest;
import kr.or.study.springcqrs.system.menu.dto.web.UpdateMenuWebRequest;

import java.util.List;

@Tag(name = "메뉴 관리", description = "메뉴 관리 API")
public interface MenuApi {

    @Operation(summary = "메뉴 목록 조회 (트리)")
    ApiResponse<List<MenuResponse>> getMenuList();

    @Operation(summary = "메뉴 생성")
    ApiResponse<List<MenuResponse>> createMenu(CreateMenuWebRequest request);

    @Operation(summary = "메뉴 수정")
    ApiResponse<List<MenuResponse>> updateMenu(String menuId, UpdateMenuWebRequest request);

    @Operation(summary = "메뉴 삭제")
    ApiResponse<Void> deleteMenu(String menuId);
}
