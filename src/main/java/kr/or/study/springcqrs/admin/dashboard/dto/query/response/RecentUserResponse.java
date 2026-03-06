package kr.or.study.springcqrs.admin.dashboard.dto.query.response;

public record RecentUserResponse(
        String id,
        String username,
        String name,
        String email,
        String createdAt
) {}
