package kr.or.study.springcqrs.admin.dashboard.service.query;

import kr.or.study.springcqrs.admin.dashboard.dto.query.response.AdminDashboardStatsResponse;
import kr.or.study.springcqrs.admin.dashboard.dto.query.response.RecentUserResponse;
import kr.or.study.springcqrs.admin.dashboard.mapper.query.AdminDashboardQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminDashboardQueryService {

    private final AdminDashboardQueryMapper adminDashboardQueryMapper;

    public AdminDashboardStatsResponse getStats() {
        return new AdminDashboardStatsResponse(
                adminDashboardQueryMapper.selectTotalUsers(),
                adminDashboardQueryMapper.selectTodayRegistered(),
                adminDashboardQueryMapper.selectActiveBoards(),
                adminDashboardQueryMapper.selectTodayPosts()
        );
    }

    public List<RecentUserResponse> getRecentUsers() {
        return adminDashboardQueryMapper.selectRecentUsers().stream()
                .map(this::toRecentUserResponse)
                .toList();
    }

    private RecentUserResponse toRecentUserResponse(Map<String, Object> row) {
        String userId = (String) row.get("user_id");
        String createdAt = row.get("created_date") instanceof Timestamp ts
                ? ts.toLocalDateTime().toString()
                : String.valueOf(row.get("created_date"));

        return new RecentUserResponse(
                userId,
                userId,
                (String) row.get("user_name"),
                (String) row.get("email"),
                createdAt
        );
    }
}
