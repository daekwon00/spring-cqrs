package kr.or.study.springcqrs.admin.user.dto.command.request;

public record AdminCreateUserRequest(
        String userId,
        String password,
        String userName,
        String email,
        String role,
        String department,
        String position
) {}
