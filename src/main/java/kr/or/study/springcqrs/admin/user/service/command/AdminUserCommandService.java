package kr.or.study.springcqrs.admin.user.service.command;

import kr.or.study.springcqrs.admin.user.dto.command.request.AdminCreateUserRequest;
import kr.or.study.springcqrs.admin.user.dto.command.request.AdminUpdateUserRequest;
import kr.or.study.springcqrs.admin.user.mapper.command.AdminUserCommandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AdminUserCommandService {

    private final AdminUserCommandMapper adminUserCommandMapper;

    public void createUser(AdminCreateUserRequest request) {
        adminUserCommandMapper.insertUser(request);
        String roleId = mapRoleToRoleId(request.role());
        adminUserCommandMapper.insertUserRole(request.userId(), roleId);
    }

    public void updateUser(AdminUpdateUserRequest request) {
        adminUserCommandMapper.updateUser(request);
        adminUserCommandMapper.deleteUserRoles(request.userId());
        String roleId = mapRoleToRoleId(request.role());
        adminUserCommandMapper.insertUserRole(request.userId(), roleId);
    }

    public void toggleActive(String userId) {
        adminUserCommandMapper.toggleActive(userId);
    }

    private String mapRoleToRoleId(String role) {
        return "ADMIN".equals(role) ? "ROLE_ADMIN" : "ROLE_MEMBER";
    }
}
