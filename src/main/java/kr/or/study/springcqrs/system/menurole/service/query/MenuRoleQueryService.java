package kr.or.study.springcqrs.system.menurole.service.query;

import kr.or.study.springcqrs.system.menurole.dto.query.response.MenuRoleResponse;
import kr.or.study.springcqrs.system.menurole.mapper.query.MenuRoleQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuRoleQueryService {

    private final MenuRoleQueryMapper menuRoleQueryMapper;

    public List<MenuRoleResponse> getAllMenuRoles() {
        return menuRoleQueryMapper.selectAllMenuRoles();
    }
}
