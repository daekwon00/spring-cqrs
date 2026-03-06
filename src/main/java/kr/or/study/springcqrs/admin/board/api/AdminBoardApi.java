package kr.or.study.springcqrs.admin.board.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.admin.board.dto.query.response.AdminBoardResponse;
import kr.or.study.springcqrs.admin.board.dto.web.CreateBoardWebRequest;
import kr.or.study.springcqrs.admin.board.dto.web.UpdateBoardWebRequest;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;

import java.util.List;

@Tag(name = "관리자 게시판 관리", description = "관리자 게시판 관리 API")
public interface AdminBoardApi {

    @Operation(summary = "게시판 목록 조회")
    ApiResponse<List<AdminBoardResponse>> getBoardList();

    @Operation(summary = "게시판 생성")
    ApiResponse<AdminBoardResponse> createBoard(CreateBoardWebRequest request);

    @Operation(summary = "게시판 수정")
    ApiResponse<AdminBoardResponse> updateBoard(String boardId, UpdateBoardWebRequest request);

    @Operation(summary = "게시판 활성/비활성 토글")
    ApiResponse<AdminBoardResponse> toggleActive(String boardId);
}
