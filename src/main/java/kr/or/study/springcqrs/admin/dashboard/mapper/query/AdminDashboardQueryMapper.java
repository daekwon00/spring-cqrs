package kr.or.study.springcqrs.admin.dashboard.mapper.query;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminDashboardQueryMapper {

    long selectTotalUsers();

    long selectTodayRegistered();

    long selectActiveBoards();

    long selectTodayPosts();

    List<Map<String, Object>> selectRecentUsers();
}
