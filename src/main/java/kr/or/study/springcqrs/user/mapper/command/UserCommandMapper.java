package kr.or.study.springcqrs.user.mapper.command;

import kr.or.study.springcqrs.user.dto.command.request.ChangePasswordRequest;
import kr.or.study.springcqrs.user.dto.command.request.UpdateProfileRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCommandMapper {

    int updateProfile(UpdateProfileRequest request);

    int updatePassword(ChangePasswordRequest request);
}
