package kr.or.study.springcqrs.admin.user.api;

import kr.or.study.springcqrs.admin.user.dto.query.condition.AdminUserSearch;
import kr.or.study.springcqrs.admin.user.dto.query.response.AdminUserResponse;
import kr.or.study.springcqrs.admin.user.dto.web.CreateUserWebRequest;
import kr.or.study.springcqrs.admin.user.dto.web.UpdateUserWebRequest;
import kr.or.study.springcqrs.admin.user.service.command.AdminUserCommandService;
import kr.or.study.springcqrs.admin.user.service.query.AdminUserQueryService;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.common.dto.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/users")
public class AdminUserController implements AdminUserApi {

    private final AdminUserQueryService adminUserQueryService;
    private final AdminUserCommandService adminUserCommandService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @GetMapping
    public ApiResponse<PageResponse<AdminUserResponse>> getUserList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search
    ) {
        AdminUserSearch condition = new AdminUserSearch(page, size, search);
        List<AdminUserResponse> content = adminUserQueryService.getUserList(condition);
        long totalElements = adminUserQueryService.getUserCount(condition);
        return ApiResponse.ok(PageResponse.of(content, totalElements, page, size));
    }

    @Override
    @GetMapping("/{userId}")
    public ApiResponse<AdminUserResponse> getUserById(@PathVariable String userId) {
        return ApiResponse.ok(adminUserQueryService.getUserById(userId));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AdminUserResponse> createUser(@Validated @RequestBody CreateUserWebRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        adminUserCommandService.createUser(request.toCommand(encodedPassword));
        return ApiResponse.ok(adminUserQueryService.getUserById(request.username()));
    }

    @Override
    @PutMapping("/{userId}")
    public ApiResponse<AdminUserResponse> updateUser(
            @PathVariable String userId,
            @Validated @RequestBody UpdateUserWebRequest request
    ) {
        adminUserCommandService.updateUser(request.toCommand(userId));
        return ApiResponse.ok(adminUserQueryService.getUserById(userId));
    }

    @Override
    @PatchMapping("/{userId}/toggle-active")
    public ApiResponse<AdminUserResponse> toggleActive(@PathVariable String userId) {
        adminUserCommandService.toggleActive(userId);
        return ApiResponse.ok(adminUserQueryService.getUserById(userId));
    }
}
