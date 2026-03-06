package kr.or.study.springcqrs.admin.user.mapper.query;

import kr.or.study.springcqrs.admin.user.dto.query.condition.AdminUserSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminUserQueryMapper {

    List<Map<String, Object>> selectUserList(@Param("search") AdminUserSearch search);

    long selectUserCount(@Param("search") AdminUserSearch search);

    Map<String, Object> selectUserById(@Param("userId") String userId);
}
