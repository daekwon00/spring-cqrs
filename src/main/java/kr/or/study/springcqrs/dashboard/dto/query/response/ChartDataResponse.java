package kr.or.study.springcqrs.dashboard.dto.query.response;

public record ChartDataResponse(
        String date,
        long count
) {}
