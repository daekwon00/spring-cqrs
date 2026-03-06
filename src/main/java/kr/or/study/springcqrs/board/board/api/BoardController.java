package kr.or.study.springcqrs.board.board.api;

import kr.or.study.springcqrs.board.board.dto.query.response.BoardResponse;
import kr.or.study.springcqrs.board.board.service.query.BoardQueryService;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardController implements BoardApi {

    private final BoardQueryService boardQueryService;

    @Override
    @GetMapping
    public ApiResponse<List<BoardResponse>> getBoardList() {
        return ApiResponse.ok(boardQueryService.getBoardList());
    }

    @Override
    @GetMapping("/{boardId}")
    public ApiResponse<BoardResponse> getBoardById(@PathVariable String boardId) {
        return ApiResponse.ok(boardQueryService.getBoardById(boardId));
    }
}
