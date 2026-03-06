package kr.or.study.springcqrs.system.role.mapper.query;

import kr.or.study.springcqrs.system.role.dto.query.response.RoleResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleQueryMapper {

    List<RoleResponse> selectRoleList();

    RoleResponse selectRoleById(@Param("roleId") String roleId);
}
