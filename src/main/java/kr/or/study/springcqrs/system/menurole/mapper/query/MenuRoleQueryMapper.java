package kr.or.study.springcqrs.system.menurole.mapper.query;

import kr.or.study.springcqrs.system.menurole.dto.query.response.MenuRoleResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuRoleQueryMapper {

    List<MenuRoleResponse> selectAllMenuRoles();
}
