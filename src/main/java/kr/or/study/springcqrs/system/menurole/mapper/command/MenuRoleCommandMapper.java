package kr.or.study.springcqrs.system.menurole.mapper.command;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MenuRoleCommandMapper {

    void deleteByRoleId(@Param("roleId") String roleId);

    void insertMenuRole(@Param("roleId") String roleId, @Param("menuId") String menuId,
                        @Param("createdBy") String createdBy);
}
