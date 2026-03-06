package kr.or.study.springcqrs.system.positionrole.mapper.command;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PositionRoleCommandMapper {

    void deleteByPositionId(@Param("positionId") String positionId);

    void insertPositionRole(@Param("positionId") String positionId, @Param("roleId") String roleId,
                            @Param("createdBy") String createdBy);
}
