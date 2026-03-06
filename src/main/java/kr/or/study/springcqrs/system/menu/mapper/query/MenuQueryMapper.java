package kr.or.study.springcqrs.system.menu.mapper.query;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuQueryMapper {

    List<Map<String, Object>> selectAllMenus();

    List<Map<String, Object>> selectMenusByRoleIds(@Param("roleIds") List<String> roleIds);

    List<Map<String, Object>> selectAdminMenus();
}
