package kr.or.study.springcqrs.admin.user.dto.query.response;

public record AdminUserResponse(
        String id,
        String username,
        String name,
        String email,
        String phone,
        String role,
        String department,
        String position,
        String createdAt,
        boolean isActive,
        String lastLoginAt
) {}
