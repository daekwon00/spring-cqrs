package kr.or.study.springcqrs.system.role.service.query;

import kr.or.study.springcqrs.common.exception.NotFoundException;
import kr.or.study.springcqrs.system.role.dto.query.response.RoleResponse;
import kr.or.study.springcqrs.system.role.mapper.query.RoleQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleQueryService {

    private final RoleQueryMapper roleQueryMapper;

    public List<RoleResponse> getRoleList() {
        return roleQueryMapper.selectRoleList();
    }

    public RoleResponse getRoleById(String roleId) {
        RoleResponse role = roleQueryMapper.selectRoleById(roleId);
        if (role == null) {
            throw new NotFoundException("역할을 찾을 수 없습니다.");
        }
        return role;
    }
}
