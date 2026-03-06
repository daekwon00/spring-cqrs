package kr.or.study.springcqrs.system.role.service.command;

import kr.or.study.springcqrs.system.role.mapper.command.RoleCommandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class RoleCommandService {

    private final RoleCommandMapper roleCommandMapper;

    public void createRole(String roleId, String roleName, String description, String userId) {
        roleCommandMapper.insertRole(roleId, roleName, description, userId);
    }

    public void updateRole(String roleId, String roleName, String description, String userId) {
        roleCommandMapper.updateRole(roleId, roleName, description, userId);
    }

    public void deleteRole(String roleId) {
        roleCommandMapper.deleteRole(roleId);
    }
}
