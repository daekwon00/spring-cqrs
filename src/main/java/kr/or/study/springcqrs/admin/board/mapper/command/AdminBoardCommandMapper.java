package kr.or.study.springcqrs.admin.board.mapper.command;

import kr.or.study.springcqrs.admin.board.dto.command.request.AdminCreateBoardRequest;
import kr.or.study.springcqrs.admin.board.dto.command.request.AdminUpdateBoardRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminBoardCommandMapper {

    void insertBoard(@Param("req") AdminCreateBoardRequest request);

    void updateBoard(@Param("req") AdminUpdateBoardRequest request);

    void toggleActive(@Param("boardId") String boardId);
}
