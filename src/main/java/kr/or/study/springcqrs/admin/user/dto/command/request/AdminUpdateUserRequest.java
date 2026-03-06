package kr.or.study.springcqrs.admin.user.dto.command.request;

public record AdminUpdateUserRequest(
        String userId,
        String userName,
        String email,
        String role,
        String department,
        String position
) {}
