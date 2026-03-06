package kr.or.study.springcqrs.system.positionrole.api;

import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.config.security.SecurityUtils;
import kr.or.study.springcqrs.system.positionrole.dto.query.response.PositionRoleResponse;
import kr.or.study.springcqrs.system.positionrole.dto.web.UpdatePositionRoleWebRequest;
import kr.or.study.springcqrs.system.positionrole.service.command.PositionRoleCommandService;
import kr.or.study.springcqrs.system.positionrole.service.query.PositionRoleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/position-roles")
public class PositionRoleController implements PositionRoleApi {

    private final PositionRoleQueryService positionRoleQueryService;
    private final PositionRoleCommandService positionRoleCommandService;

    @Override
    @GetMapping
    public ApiResponse<List<PositionRoleResponse>> getAllPositionRoles() {
        return ApiResponse.ok(positionRoleQueryService.getAllPositionRoles());
    }

    @Override
    @PutMapping("/{positionId}")
    public ApiResponse<Void> updatePositionRoles(
            @PathVariable String positionId,
            @RequestBody UpdatePositionRoleWebRequest request
    ) {
        String userId = SecurityUtils.getCurrentUserId();
        positionRoleCommandService.updatePositionRoles(positionId, request.roleIds(), userId);
        return ApiResponse.ok(null);
    }
}
