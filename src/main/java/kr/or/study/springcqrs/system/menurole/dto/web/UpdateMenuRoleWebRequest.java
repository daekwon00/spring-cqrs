package kr.or.study.springcqrs.system.menurole.dto.web;

import java.util.List;

public record UpdateMenuRoleWebRequest(
        List<String> menuIds
) {}
