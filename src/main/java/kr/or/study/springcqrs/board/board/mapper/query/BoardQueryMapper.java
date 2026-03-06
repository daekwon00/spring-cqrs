package kr.or.study.springcqrs.board.board.mapper.query;

import kr.or.study.springcqrs.board.board.dto.query.response.BoardResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardQueryMapper {

    List<BoardResponse> selectBoardList();

    BoardResponse selectBoardById(@Param("boardId") String boardId);
}
