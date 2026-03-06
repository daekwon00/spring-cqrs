package kr.or.study.springcqrs.system.positionrole.dto.web;

import java.util.List;

public record UpdatePositionRoleWebRequest(
        List<String> roleIds
) {}
