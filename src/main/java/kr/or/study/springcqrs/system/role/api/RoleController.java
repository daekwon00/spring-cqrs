package kr.or.study.springcqrs.system.role.api;

import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.config.security.SecurityUtils;
import kr.or.study.springcqrs.system.role.dto.query.response.RoleResponse;
import kr.or.study.springcqrs.system.role.dto.web.CreateRoleWebRequest;
import kr.or.study.springcqrs.system.role.dto.web.UpdateRoleWebRequest;
import kr.or.study.springcqrs.system.role.service.command.RoleCommandService;
import kr.or.study.springcqrs.system.role.service.query.RoleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/roles")
public class RoleController implements RoleApi {

    private final RoleQueryService roleQueryService;
    private final RoleCommandService roleCommandService;

    @Override
    @GetMapping
    public ApiResponse<List<RoleResponse>> getRoleList() {
        return ApiResponse.ok(roleQueryService.getRoleList());
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<RoleResponse> createRole(@Validated @RequestBody CreateRoleWebRequest request) {
        String userId = SecurityUtils.getCurrentUserId();
        roleCommandService.createRole(request.id(), request.name(), request.description(), userId);
        return ApiResponse.ok(roleQueryService.getRoleById(request.id()));
    }

    @Override
    @PutMapping("/{roleId}")
    public ApiResponse<RoleResponse> updateRole(
            @PathVariable String roleId,
            @Validated @RequestBody UpdateRoleWebRequest request
    ) {
        String userId = SecurityUtils.getCurrentUserId();
        roleCommandService.updateRole(roleId, request.name(), request.description(), userId);
        return ApiResponse.ok(roleQueryService.getRoleById(roleId));
    }

    @Override
    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deleteRole(@PathVariable String roleId) {
        roleCommandService.deleteRole(roleId);
        return ApiResponse.ok(null);
    }
}
