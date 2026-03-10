package kr.or.study.springcqrs.user.service.query;

import kr.or.study.springcqrs.common.exception.NotFoundException;
import kr.or.study.springcqrs.user.dto.query.response.LoginHistoryResponse;
import kr.or.study.springcqrs.user.dto.query.response.UserProfileResponse;
import kr.or.study.springcqrs.user.mapper.query.UserQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserQueryMapper userQueryMapper;

    public UserProfileResponse getUserProfile(String userId) {
        UserProfileResponse profile = userQueryMapper.selectUserProfile(userId);
        if (profile == null) {
            throw new NotFoundException("사용자를 찾을 수 없습니다.");
        }
        return profile;
    }

    public List<LoginHistoryResponse> getLoginHistory(String userId, int limit) {
        return userQueryMapper.selectLoginHistory(userId, limit);
    }
}
