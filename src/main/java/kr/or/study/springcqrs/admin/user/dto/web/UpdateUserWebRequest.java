package kr.or.study.springcqrs.admin.user.dto.web;

import jakarta.validation.constraints.NotBlank;
import kr.or.study.springcqrs.admin.user.dto.command.request.AdminUpdateUserRequest;

public record UpdateUserWebRequest(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String role,
        String department,
        String position
) {
    public AdminUpdateUserRequest toCommand(String userId) {
        return new AdminUpdateUserRequest(userId, name, email, role, department, position);
    }
}
