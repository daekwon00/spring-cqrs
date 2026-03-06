package kr.or.study.springcqrs.user.service.command;

import kr.or.study.springcqrs.auth.mapper.query.AuthQueryMapper;
import kr.or.study.springcqrs.common.exception.BusinessException;
import kr.or.study.springcqrs.user.dto.command.request.ChangePasswordRequest;
import kr.or.study.springcqrs.user.dto.command.request.UpdateProfileRequest;
import kr.or.study.springcqrs.user.dto.query.response.UserProfileResponse;
import kr.or.study.springcqrs.user.mapper.command.UserCommandMapper;
import kr.or.study.springcqrs.user.service.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserCommandService {

    private final UserCommandMapper userCommandMapper;
    private final UserQueryService userQueryService;
    private final AuthQueryMapper authQueryMapper;
    private final PasswordEncoder passwordEncoder;

    public UserProfileResponse updateProfile(String userId, UpdateProfileRequest request) {
        userCommandMapper.updateProfile(
                new UpdateProfileRequest(userId, request.userName(), request.email(), request.phone()));
        return userQueryService.getUserProfile(userId);
    }

    public void changePassword(String userId, String currentPassword, String newPassword) {
        Map<String, Object> user = authQueryMapper.selectUserById(userId);
        if (user == null) {
            throw new BusinessException("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        String storedPassword = (String) user.get("password");
        if (!matchesPassword(currentPassword, storedPassword)) {
            throw new BusinessException("현재 비밀번호가 올바르지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(newPassword);
        userCommandMapper.updatePassword(new ChangePasswordRequest(userId, encodedPassword));
    }

    private boolean matchesPassword(String rawPassword, String storedPassword) {
        if (storedPassword.startsWith("$2")) {
            return passwordEncoder.matches(rawPassword, storedPassword);
        }
        return rawPassword.equals(storedPassword);
    }
}
