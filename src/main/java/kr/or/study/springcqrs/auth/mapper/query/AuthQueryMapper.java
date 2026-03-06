package kr.or.study.springcqrs.auth.mapper.query;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AuthQueryMapper {

    Map<String, Object> selectUserById(@Param("userId") String userId);

    List<String> selectRolesByUserId(@Param("userId") String userId);

    boolean existsByUserId(@Param("userId") String userId);
}
