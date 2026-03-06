package kr.or.study.springcqrs.admin.user.service.query;

import kr.or.study.springcqrs.admin.user.dto.query.condition.AdminUserSearch;
import kr.or.study.springcqrs.admin.user.dto.query.response.AdminUserResponse;
import kr.or.study.springcqrs.admin.user.mapper.query.AdminUserQueryMapper;
import kr.or.study.springcqrs.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminUserQueryService {

    private final AdminUserQueryMapper adminUserQueryMapper;

    public List<AdminUserResponse> getUserList(AdminUserSearch search) {
        return adminUserQueryMapper.selectUserList(search).stream()
                .map(this::toAdminUserResponse)
                .toList();
    }

    public long getUserCount(AdminUserSearch search) {
        return adminUserQueryMapper.selectUserCount(search);
    }

    public AdminUserResponse getUserById(String userId) {
        Map<String, Object> row = adminUserQueryMapper.selectUserById(userId);
        if (row == null) {
            throw new NotFoundException("사용자를 찾을 수 없습니다.");
        }
        return toAdminUserResponse(row);
    }

    private AdminUserResponse toAdminUserResponse(Map<String, Object> row) {
        String userId = (String) row.get("user_id");
        String roleName = mapRole((String) row.get("role_id"));

        return new AdminUserResponse(
                userId,
                userId,
                (String) row.get("user_name"),
                (String) row.get("email"),
                (String) row.get("phone"),
                roleName,
                (String) row.get("department"),
                (String) row.get("position_name"),
                toIsoString(row.get("created_date")),
                Boolean.TRUE.equals(row.get("is_active")),
                toIsoString(row.get("last_login_date"))
        );
    }

    private String mapRole(String roleId) {
        if (roleId == null) return "USER";
        return switch (roleId) {
            case "ROLE_SUPER_ADMIN", "ROLE_ADMIN" -> "ADMIN";
            default -> "USER";
        };
    }

    private String toIsoString(Object value) {
        if (value == null) return null;
        if (value instanceof Timestamp ts) return ts.toLocalDateTime().toString();
        return String.valueOf(value);
    }
}
