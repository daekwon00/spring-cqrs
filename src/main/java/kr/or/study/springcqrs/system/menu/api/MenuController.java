package kr.or.study.springcqrs.system.menu.api;

import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.config.security.SecurityUtils;
import kr.or.study.springcqrs.system.menu.dto.query.response.MenuResponse;
import kr.or.study.springcqrs.system.menu.dto.web.CreateMenuWebRequest;
import kr.or.study.springcqrs.system.menu.dto.web.UpdateMenuWebRequest;
import kr.or.study.springcqrs.system.menu.service.command.MenuCommandService;
import kr.or.study.springcqrs.system.menu.service.query.MenuQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/menus")
public class MenuController implements MenuApi {

    private final MenuQueryService menuQueryService;
    private final MenuCommandService menuCommandService;

    @Override
    @GetMapping
    public ApiResponse<List<MenuResponse>> getMenuList() {
        return ApiResponse.ok(menuQueryService.getAllMenus());
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<List<MenuResponse>> createMenu(@Validated @RequestBody CreateMenuWebRequest request) {
        String userId = SecurityUtils.getCurrentUserId();
        menuCommandService.createMenu(request.id(), request.name(), request.path(),
                request.icon(), request.parentId(), request.sortOrder(), userId);
        return ApiResponse.ok(menuQueryService.getAllMenus());
    }

    @Override
    @PutMapping("/{menuId}")
    public ApiResponse<List<MenuResponse>> updateMenu(
            @PathVariable String menuId,
            @Validated @RequestBody UpdateMenuWebRequest request
    ) {
        String userId = SecurityUtils.getCurrentUserId();
        menuCommandService.updateMenu(menuId, request.name(), request.path(),
                request.icon(), request.parentId(), request.sortOrder(), userId);
        return ApiResponse.ok(menuQueryService.getAllMenus());
    }

    @Override
    @DeleteMapping("/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deleteMenu(@PathVariable String menuId) {
        menuCommandService.deleteMenu(menuId);
        return ApiResponse.ok(null);
    }
}
