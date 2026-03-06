package kr.or.study.springcqrs.system.menurole.service.command;

import kr.or.study.springcqrs.system.menurole.mapper.command.MenuRoleCommandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MenuRoleCommandService {

    private final MenuRoleCommandMapper menuRoleCommandMapper;

    public void updateMenuRoles(String roleId, List<String> menuIds, String userId) {
        menuRoleCommandMapper.deleteByRoleId(roleId);
        for (String menuId : menuIds) {
            menuRoleCommandMapper.insertMenuRole(roleId, menuId, userId);
        }
    }
}
