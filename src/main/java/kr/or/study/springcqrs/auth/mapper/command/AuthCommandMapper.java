package kr.or.study.springcqrs.auth.mapper.command;

import kr.or.study.springcqrs.auth.dto.command.request.LoginHistoryRequest;
import kr.or.study.springcqrs.auth.dto.command.request.RegisterRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthCommandMapper {

    int insertUser(RegisterRequest request);

    int insertUserRole(@Param("userId") String userId, @Param("roleId") String roleId);

    int updateLastLoginDate(@Param("userId") String userId);

    int insertLoginHistory(LoginHistoryRequest request);
}
