package kr.or.study.springcqrs.dashboard.service.query;

import kr.or.study.springcqrs.dashboard.dto.query.response.ChartDataResponse;
import kr.or.study.springcqrs.dashboard.dto.query.response.DashboardStatsResponse;
import kr.or.study.springcqrs.dashboard.mapper.query.DashboardQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardQueryService {

    private final DashboardQueryMapper dashboardQueryMapper;

    public DashboardStatsResponse getStats(String userId) {
        long totalPosts = dashboardQueryMapper.selectTotalPosts();
        long todayPosts = dashboardQueryMapper.selectTodayPosts();
        long totalUsers = dashboardQueryMapper.selectTotalUsers();
        long myPosts = dashboardQueryMapper.selectMyPosts(userId);

        return new DashboardStatsResponse(totalPosts, todayPosts, totalUsers, myPosts);
    }

    public List<ChartDataResponse> getChartData(String period) {
        int days = "30d".equals(period) ? 30 : 7;
        return dashboardQueryMapper.selectChartData(days);
    }
}
