package kr.or.study.springcqrs.admin.user.mapper.command;

import kr.or.study.springcqrs.admin.user.dto.command.request.AdminCreateUserRequest;
import kr.or.study.springcqrs.admin.user.dto.command.request.AdminUpdateUserRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminUserCommandMapper {

    void insertUser(@Param("req") AdminCreateUserRequest request);

    void insertUserRole(@Param("userId") String userId, @Param("roleId") String roleId);

    void updateUser(@Param("req") AdminUpdateUserRequest request);

    void deleteUserRoles(@Param("userId") String userId);

    void toggleActive(@Param("userId") String userId);
}
