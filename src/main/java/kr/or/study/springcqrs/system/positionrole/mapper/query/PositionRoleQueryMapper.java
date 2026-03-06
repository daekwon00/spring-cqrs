package kr.or.study.springcqrs.system.positionrole.mapper.query;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PositionRoleQueryMapper {

    List<Map<String, Object>> selectAllPositionRoles();
}
