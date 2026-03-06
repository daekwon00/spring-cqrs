package kr.or.study.springcqrs.admin.dashboard.dto.query.response;

public record AdminDashboardStatsResponse(
        long totalUsers,
        long todayRegistered,
        long activeBoards,
        long todayPosts
) {}
