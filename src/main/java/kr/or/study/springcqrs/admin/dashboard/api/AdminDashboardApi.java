package kr.or.study.springcqrs.admin.dashboard.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.admin.dashboard.dto.query.response.AdminDashboardStatsResponse;
import kr.or.study.springcqrs.admin.dashboard.dto.query.response.RecentUserResponse;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;

import java.util.List;

@Tag(name = "관리자 대시보드", description = "관리자 대시보드 API")
public interface AdminDashboardApi {

    @Operation(summary = "관리자 대시보드 통계 조회")
    ApiResponse<AdminDashboardStatsResponse> getStats();

    @Operation(summary = "최근 가입 사용자 조회")
    ApiResponse<List<RecentUserResponse>> getRecentUsers();
}
