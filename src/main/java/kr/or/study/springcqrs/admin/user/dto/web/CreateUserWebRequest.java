package kr.or.study.springcqrs.admin.user.dto.web;

import jakarta.validation.constraints.NotBlank;
import kr.or.study.springcqrs.admin.user.dto.command.request.AdminCreateUserRequest;

public record CreateUserWebRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String role,
        String department,
        String position
) {
    public AdminCreateUserRequest toCommand(String encodedPassword) {
        return new AdminCreateUserRequest(
                username, encodedPassword, name, email, role, department, position
        );
    }
}
