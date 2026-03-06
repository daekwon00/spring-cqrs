package kr.or.study.springcqrs.auth.service.query;

import kr.or.study.springcqrs.auth.mapper.query.AuthQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthQueryService {

    private final AuthQueryMapper authQueryMapper;

    public Map<String, Object> getUserById(String userId) {
        return authQueryMapper.selectUserById(userId);
    }

    public List<String> getRolesByUserId(String userId) {
        return authQueryMapper.selectRolesByUserId(userId);
    }

    public boolean existsByUserId(String userId) {
        return authQueryMapper.existsByUserId(userId);
    }
}
