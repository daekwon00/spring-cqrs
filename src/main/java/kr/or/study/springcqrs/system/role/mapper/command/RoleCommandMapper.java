package kr.or.study.springcqrs.system.role.mapper.command;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleCommandMapper {

    void insertRole(@Param("roleId") String roleId, @Param("roleName") String roleName,
                    @Param("description") String description, @Param("createdBy") String createdBy);

    void updateRole(@Param("roleId") String roleId, @Param("roleName") String roleName,
                    @Param("description") String description, @Param("modifiedBy") String modifiedBy);

    void deleteRole(@Param("roleId") String roleId);
}
