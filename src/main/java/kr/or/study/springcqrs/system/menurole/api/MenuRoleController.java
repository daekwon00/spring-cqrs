package kr.or.study.springcqrs.system.menurole.api;

import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.config.security.SecurityUtils;
import kr.or.study.springcqrs.system.menurole.dto.query.response.MenuRoleResponse;
import kr.or.study.springcqrs.system.menurole.dto.web.UpdateMenuRoleWebRequest;
import kr.or.study.springcqrs.system.menurole.service.command.MenuRoleCommandService;
import kr.or.study.springcqrs.system.menurole.service.query.MenuRoleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/menu-roles")
public class MenuRoleController implements MenuRoleApi {

    private final MenuRoleQueryService menuRoleQueryService;
    private final MenuRoleCommandService menuRoleCommandService;

    @Override
    @GetMapping
    public ApiResponse<List<MenuRoleResponse>> getAllMenuRoles() {
        return ApiResponse.ok(menuRoleQueryService.getAllMenuRoles());
    }

    @Override
    @PutMapping("/{roleId}")
    public ApiResponse<Void> updateMenuRoles(
            @PathVariable String roleId,
            @RequestBody UpdateMenuRoleWebRequest request
    ) {
        String userId = SecurityUtils.getCurrentUserId();
        menuRoleCommandService.updateMenuRoles(roleId, request.menuIds(), userId);
        return ApiResponse.ok(null);
    }
}
