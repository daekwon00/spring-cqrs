package kr.or.study.springcqrs.dashboard.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.dashboard.dto.query.response.ChartDataResponse;
import kr.or.study.springcqrs.dashboard.dto.query.response.DashboardStatsResponse;

import java.util.List;

@Tag(name = "대시보드", description = "대시보드 API")
public interface DashboardApi {

    @Operation(summary = "대시보드 통계 조회")
    ApiResponse<DashboardStatsResponse> getStats();

    @Operation(summary = "대시보드 차트 데이터 조회")
    ApiResponse<List<ChartDataResponse>> getChartData(String period);
}
