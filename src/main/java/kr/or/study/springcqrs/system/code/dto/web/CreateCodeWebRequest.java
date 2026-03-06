package kr.or.study.springcqrs.system.code.dto.web;

import jakarta.validation.constraints.NotBlank;

public record CreateCodeWebRequest(
        @NotBlank String groupCode,
        @NotBlank String code,
        @NotBlank String name,
        String value,
        int sortOrder,
        String description
) {}
