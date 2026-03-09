package kr.or.study.springcqrs.auth.service.command;

import kr.or.study.springcqrs.auth.dto.command.request.LoginHistoryRequest;
import kr.or.study.springcqrs.auth.dto.command.request.RegisterRequest;
import kr.or.study.springcqrs.auth.dto.query.response.LoginResponse;
import kr.or.study.springcqrs.auth.dto.query.response.UserInfoResponse;
import kr.or.study.springcqrs.auth.dto.web.LoginWebRequest;
import kr.or.study.springcqrs.auth.dto.web.RegisterWebRequest;
import kr.or.study.springcqrs.auth.mapper.command.AuthCommandMapper;
import kr.or.study.springcqrs.auth.service.query.AuthQueryService;
import kr.or.study.springcqrs.common.exception.BusinessException;
import kr.or.study.springcqrs.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AuthCommandService {

    private final AuthCommandMapper authCommandMapper;
    private final AuthQueryService authQueryService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginWebRequest request, String loginIp) {
        Map<String, Object> user = authQueryService.getUserById(request.username());

        if (user == null) {
            recordLoginHistory(request.username(), loginIp, false, "사용자를 찾을 수 없습니다.");
            throw new BusinessException("아이디 또는 비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED);
        }

        Boolean isActive = (Boolean) user.get("is_active");
        if (isActive != null && !isActive) {
            recordLoginHistory(request.username(), loginIp, false, "비활성 계정");
            throw new BusinessException("비활성화된 계정입니다.", HttpStatus.UNAUTHORIZED);
        }

        String storedPassword = (String) user.get("password");
        boolean passwordMatch = matchesPassword(request.password(), storedPassword);

        if (!passwordMatch) {
            recordLoginHistory(request.username(), loginIp, false, "비밀번호 불일치");
            throw new BusinessException("아이디 또는 비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED);
        }

        List<String> roles = authQueryService.getRolesByUserId(request.username());
        String accessToken = jwtTokenProvider.createAccessToken(request.username(), roles);
        String refreshToken = jwtTokenProvider.createRefreshToken(request.username());

        authCommandMapper.updateLastLoginDate(request.username());
        recordLoginHistory(request.username(), loginIp, true, null);

        // role 변환: ROLE_SUPER_ADMIN, ROLE_ADMIN → ADMIN / 그 외 → USER
        String displayRole = roles.stream()
                .anyMatch(r -> r.contains("ADMIN")) ? "ADMIN" : "USER";

        UserInfoResponse userInfo = new UserInfoResponse(
                (String) user.get("user_id"),
                (String) user.get("user_id"),
                (String) user.get("user_name"),
                (String) user.get("email"),
                displayRole
        );

        return new LoginResponse(accessToken, refreshToken, userInfo);
    }

    public UserInfoResponse register(RegisterWebRequest request) {
        if (authQueryService.existsByUserId(request.username())) {
            throw new BusinessException("이미 존재하는 아이디입니다.", HttpStatus.CONFLICT);
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        RegisterRequest command = request.toCommand(encodedPassword);

        authCommandMapper.insertUser(command);
        authCommandMapper.insertUserRole(request.username(), "ROLE_MEMBER");

        return new UserInfoResponse(request.username(), request.username(), request.name(), request.email(), "USER");
    }

    private void recordLoginHistory(String userId, String loginIp, boolean success, String failReason) {
        authCommandMapper.insertLoginHistory(
                new LoginHistoryRequest(userId, loginIp, success, failReason));
    }

    /**
     * BCrypt 해시 또는 평문 비밀번호 모두 지원
     * (기존 DB에 평문 비밀번호가 있어 호환 처리)
     */
    private boolean matchesPassword(String rawPassword, String storedPassword) {
        if (storedPassword.startsWith("$2")) {
            return passwordEncoder.matches(rawPassword, storedPassword);
        }
        return rawPassword.equals(storedPassword);
    }
}
