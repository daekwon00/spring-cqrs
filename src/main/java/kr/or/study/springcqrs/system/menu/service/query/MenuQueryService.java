package kr.or.study.springcqrs.system.menu.service.query;

import kr.or.study.springcqrs.system.menu.dto.query.response.MenuResponse;
import kr.or.study.springcqrs.system.menu.mapper.query.MenuQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuQueryService {

    private final MenuQueryMapper menuQueryMapper;

    public List<MenuResponse> getAllMenus() {
        return buildTree(menuQueryMapper.selectAllMenus());
    }

    public List<MenuResponse> getMenusByRoleIds(List<String> roleIds) {
        return buildTree(menuQueryMapper.selectMenusByRoleIds(roleIds));
    }

    public List<MenuResponse> getAdminMenus() {
        return buildTree(menuQueryMapper.selectAdminMenus());
    }

    private List<MenuResponse> buildTree(List<Map<String, Object>> rows) {
        List<MenuResponse> flatList = rows.stream().map(this::toMenuResponse).toList();
        List<MenuResponse> roots = new ArrayList<>();

        for (MenuResponse menu : flatList) {
            if (menu.parentId() == null) {
                List<MenuResponse> children = flatList.stream()
                        .filter(m -> menu.id().equals(m.parentId()))
                        .toList();
                roots.add(new MenuResponse(
                        menu.id(), menu.name(), menu.path(), menu.icon(),
                        menu.parentId(), menu.sortOrder(), menu.isActive(), children
                ));
            }
        }
        return roots;
    }

    private MenuResponse toMenuResponse(Map<String, Object> row) {
        return new MenuResponse(
                (String) row.get("menu_id"),
                (String) row.get("menu_name"),
                (String) row.get("menu_url"),
                (String) row.get("menu_icon"),
                (String) row.get("parent_menu_id"),
                row.get("sort_order") != null ? ((Number) row.get("sort_order")).intValue() : 0,
                Boolean.TRUE.equals(row.get("use_yn")),
                List.of()
        );
    }
}
