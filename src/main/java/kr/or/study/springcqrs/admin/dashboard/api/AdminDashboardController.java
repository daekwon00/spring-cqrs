package kr.or.study.springcqrs.admin.dashboard.api;

import kr.or.study.springcqrs.admin.dashboard.dto.query.response.AdminDashboardStatsResponse;
import kr.or.study.springcqrs.admin.dashboard.dto.query.response.RecentUserResponse;
import kr.or.study.springcqrs.admin.dashboard.service.query.AdminDashboardQueryService;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/dashboard")
public class AdminDashboardController implements AdminDashboardApi {

    private final AdminDashboardQueryService adminDashboardQueryService;

    @Override
    @GetMapping("/stats")
    public ApiResponse<AdminDashboardStatsResponse> getStats() {
        return ApiResponse.ok(adminDashboardQueryService.getStats());
    }

    @Override
    @GetMapping("/recent-users")
    public ApiResponse<List<RecentUserResponse>> getRecentUsers() {
        return ApiResponse.ok(adminDashboardQueryService.getRecentUsers());
    }
}
