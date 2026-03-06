package kr.or.study.springcqrs.system.role.dto.web;

import jakarta.validation.constraints.NotBlank;

public record UpdateRoleWebRequest(
        @NotBlank String name,
        String description
) {}
