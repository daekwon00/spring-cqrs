package kr.or.study.springcqrs.system.positionrole.dto.query.response;

import java.util.List;

public record PositionRoleResponse(
        String positionId,
        String positionName,
        List<String> roleIds
) {}
