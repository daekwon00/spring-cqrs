package kr.or.study.springcqrs.system.menu.dto.query.response;

import java.util.List;

public record MenuResponse(
        String id,
        String name,
        String path,
        String icon,
        String parentId,
        int sortOrder,
        boolean isActive,
        List<MenuResponse> children
) {}
