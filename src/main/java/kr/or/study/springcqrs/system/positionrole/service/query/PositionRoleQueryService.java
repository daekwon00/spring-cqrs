package kr.or.study.springcqrs.system.positionrole.service.query;

import kr.or.study.springcqrs.system.positionrole.dto.query.response.PositionRoleResponse;
import kr.or.study.springcqrs.system.positionrole.mapper.query.PositionRoleQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PositionRoleQueryService {

    private final PositionRoleQueryMapper positionRoleQueryMapper;

    public List<PositionRoleResponse> getAllPositionRoles() {
        List<Map<String, Object>> rows = positionRoleQueryMapper.selectAllPositionRoles();
        Map<String, PositionRoleResponse> grouped = new LinkedHashMap<>();

        for (Map<String, Object> row : rows) {
            String positionId = (String) row.get("position_id");
            String positionName = (String) row.get("position_name");
            String roleId = (String) row.get("role_id");

            grouped.compute(positionId, (k, existing) -> {
                if (existing == null) {
                    List<String> roleIds = new ArrayList<>();
                    if (roleId != null) roleIds.add(roleId);
                    return new PositionRoleResponse(positionId, positionName, roleIds);
                } else {
                    List<String> roleIds = new ArrayList<>(existing.roleIds());
                    if (roleId != null) roleIds.add(roleId);
                    return new PositionRoleResponse(positionId, positionName, roleIds);
                }
            });
        }

        return new ArrayList<>(grouped.values());
    }
}
