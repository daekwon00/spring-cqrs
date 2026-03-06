package kr.or.study.springcqrs.system.menu.service.command;

import kr.or.study.springcqrs.system.menu.mapper.command.MenuCommandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MenuCommandService {

    private final MenuCommandMapper menuCommandMapper;

    public void createMenu(String menuId, String menuName, String menuUrl, String menuIcon,
                           String parentMenuId, int sortOrder, String userId) {
        menuCommandMapper.insertMenu(menuId, menuName, menuUrl, menuIcon, parentMenuId, sortOrder, userId);
    }

    public void updateMenu(String menuId, String menuName, String menuUrl, String menuIcon,
                           String parentMenuId, int sortOrder, String userId) {
        menuCommandMapper.updateMenu(menuId, menuName, menuUrl, menuIcon, parentMenuId, sortOrder, userId);
    }

    public void deleteMenu(String menuId) {
        menuCommandMapper.deleteMenu(menuId);
    }
}
