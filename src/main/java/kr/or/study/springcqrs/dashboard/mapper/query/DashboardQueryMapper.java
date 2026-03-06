package kr.or.study.springcqrs.dashboard.mapper.query;

import kr.or.study.springcqrs.dashboard.dto.query.response.ChartDataResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DashboardQueryMapper {

    long selectTotalPosts();

    long selectTodayPosts();

    long selectTotalUsers();

    long selectMyPosts(@Param("userId") String userId);

    List<ChartDataResponse> selectChartData(@Param("days") int days);
}
