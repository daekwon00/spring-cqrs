package kr.or.study.springcqrs.system.role.dto.web;

import jakarta.validation.constraints.NotBlank;

public record CreateRoleWebRequest(
        @NotBlank String id,
        @NotBlank String name,
        String description
) {}
