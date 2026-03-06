package kr.or.study.springcqrs.admin.board.api;

import kr.or.study.springcqrs.admin.board.dto.query.response.AdminBoardResponse;
import kr.or.study.springcqrs.admin.board.dto.web.CreateBoardWebRequest;
import kr.or.study.springcqrs.admin.board.dto.web.UpdateBoardWebRequest;
import kr.or.study.springcqrs.admin.board.service.command.AdminBoardCommandService;
import kr.or.study.springcqrs.admin.board.service.query.AdminBoardQueryService;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.config.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/boards")
public class AdminBoardController implements AdminBoardApi {

    private final AdminBoardQueryService adminBoardQueryService;
    private final AdminBoardCommandService adminBoardCommandService;

    @Override
    @GetMapping
    public ApiResponse<List<AdminBoardResponse>> getBoardList() {
        return ApiResponse.ok(adminBoardQueryService.getBoardList());
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AdminBoardResponse> createBoard(@Validated @RequestBody CreateBoardWebRequest request) {
        String userId = SecurityUtils.getCurrentUserId();
        adminBoardCommandService.createBoard(request.toCommand(userId));
        return ApiResponse.ok(adminBoardQueryService.getBoardById(request.id()));
    }

    @Override
    @PutMapping("/{boardId}")
    public ApiResponse<AdminBoardResponse> updateBoard(
            @PathVariable String boardId,
            @Validated @RequestBody UpdateBoardWebRequest request
    ) {
        String userId = SecurityUtils.getCurrentUserId();
        adminBoardCommandService.updateBoard(request.toCommand(boardId, userId));
        return ApiResponse.ok(adminBoardQueryService.getBoardById(boardId));
    }

    @Override
    @PatchMapping("/{boardId}/toggle-active")
    public ApiResponse<AdminBoardResponse> toggleActive(@PathVariable String boardId) {
        adminBoardCommandService.toggleActive(boardId);
        return ApiResponse.ok(adminBoardQueryService.getBoardById(boardId));
    }
}
