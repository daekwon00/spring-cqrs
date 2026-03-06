package kr.or.study.springcqrs.system.code.dto.query.response;

public record CodeResponse(
        String id,
        String groupCode,
        String code,
        String name,
        String value,
        int sortOrder,
        boolean isActive,
        String description
) {}
