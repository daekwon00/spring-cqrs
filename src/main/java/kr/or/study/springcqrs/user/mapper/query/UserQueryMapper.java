package kr.or.study.springcqrs.user.mapper.query;

import kr.or.study.springcqrs.user.dto.query.response.LoginHistoryResponse;
import kr.or.study.springcqrs.user.dto.query.response.UserProfileResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserQueryMapper {

    UserProfileResponse selectUserProfile(@Param("userId") String userId);

    List<LoginHistoryResponse> selectLoginHistory(@Param("userId") String userId, @Param("limit") int limit);
}
