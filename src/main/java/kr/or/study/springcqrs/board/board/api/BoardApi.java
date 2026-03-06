package kr.or.study.springcqrs.board.board.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.board.board.dto.query.response.BoardResponse;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;

import java.util.List;

@Tag(name = "게시판", description = "게시판 API")
public interface BoardApi {

    @Operation(summary = "게시판 목록 조회")
    ApiResponse<List<BoardResponse>> getBoardList();

    @Operation(summary = "게시판 상세 조회")
    ApiResponse<BoardResponse> getBoardById(String boardId);
}
