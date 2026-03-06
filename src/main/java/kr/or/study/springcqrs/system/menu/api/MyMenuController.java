package kr.or.study.springcqrs.system.menu.api;

import kr.or.study.springcqrs.auth.service.query.AuthQueryService;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.config.security.SecurityUtils;
import kr.or.study.springcqrs.system.menu.dto.query.response.MenuResponse;
import kr.or.study.springcqrs.system.menu.dto.query.response.MyMenusResponse;
import kr.or.study.springcqrs.system.menu.service.query.MenuQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menus")
public class MyMenuController implements MyMenuApi {

    private final MenuQueryService menuQueryService;
    private final AuthQueryService authQueryService;

    @Override
    @GetMapping("/my")
    public ApiResponse<MyMenusResponse> getMyMenus() {
        String userId = SecurityUtils.getCurrentUserId();
        List<String> roleIds = authQueryService.getRolesByUserId(userId);

        List<MenuResponse> menus = menuQueryService.getMenusByRoleIds(roleIds);

        boolean isAdmin = roleIds.stream()
                .anyMatch(r -> r.equals("ROLE_ADMIN") || r.equals("ROLE_SUPER_ADMIN"));
        List<MenuResponse> adminMenus = isAdmin ? menuQueryService.getAdminMenus() : List.of();

        return ApiResponse.ok(new MyMenusResponse(menus, adminMenus));
    }
}
