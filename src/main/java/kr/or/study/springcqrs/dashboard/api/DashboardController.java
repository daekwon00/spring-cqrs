package kr.or.study.springcqrs.dashboard.api;

import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.config.security.SecurityUtils;
import kr.or.study.springcqrs.dashboard.dto.query.response.ChartDataResponse;
import kr.or.study.springcqrs.dashboard.dto.query.response.DashboardStatsResponse;
import kr.or.study.springcqrs.dashboard.service.query.DashboardQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dashboard")
public class DashboardController implements DashboardApi {

    private final DashboardQueryService dashboardQueryService;

    @Override
    @GetMapping("/stats")
    public ApiResponse<DashboardStatsResponse> getStats() {
        String userId = SecurityUtils.getCurrentUserId();
        return ApiResponse.ok(dashboardQueryService.getStats(userId));
    }

    @Override
    @GetMapping("/chart")
    public ApiResponse<List<ChartDataResponse>> getChartData(
            @RequestParam(defaultValue = "7d") String period
    ) {
        return ApiResponse.ok(dashboardQueryService.getChartData(period));
    }
}
