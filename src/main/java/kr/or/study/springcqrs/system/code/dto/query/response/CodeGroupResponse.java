package kr.or.study.springcqrs.system.code.dto.query.response;

public record CodeGroupResponse(
        String id,
        String name,
        String description,
        boolean isActive
) {}
