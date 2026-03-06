package kr.or.study.springcqrs.system.menu.dto.web;

import jakarta.validation.constraints.NotBlank;

public record UpdateMenuWebRequest(
        @NotBlank String name,
        String path,
        String icon,
        String parentId,
        int sortOrder
) {}
