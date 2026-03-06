package kr.or.study.springcqrs.dashboard.dto.query.response;

public record DashboardStatsResponse(
        long totalPosts,
        long todayPosts,
        long totalUsers,
        long myPosts
) {}
