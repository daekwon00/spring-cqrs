package kr.or.study.springcqrs.system.menu.dto.query.response;

import java.util.List;

public record MyMenusResponse(
        List<MenuResponse> menus,
        List<MenuResponse> adminMenus
) {}
