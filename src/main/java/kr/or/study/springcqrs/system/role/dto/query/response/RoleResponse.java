package kr.or.study.springcqrs.system.role.dto.query.response;

public record RoleResponse(
        String id,
        String name,
        String description
) {}
