package kr.or.study.springcqrs.user.mapper.query;

import kr.or.study.springcqrs.user.dto.query.response.UserProfileResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserQueryMapper {

    UserProfileResponse selectUserProfile(@Param("userId") String userId);
}
