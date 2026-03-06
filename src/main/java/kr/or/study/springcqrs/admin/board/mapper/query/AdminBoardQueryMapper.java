package kr.or.study.springcqrs.admin.board.mapper.query;

import kr.or.study.springcqrs.admin.board.dto.query.response.AdminBoardResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminBoardQueryMapper {

    List<AdminBoardResponse> selectBoardList();

    AdminBoardResponse selectBoardById(@Param("boardId") String boardId);
}
