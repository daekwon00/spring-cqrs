package kr.or.study.springcqrs.admin.board.dto.web;

import jakarta.validation.constraints.NotBlank;
import kr.or.study.springcqrs.admin.board.dto.command.request.AdminUpdateBoardRequest;

public record UpdateBoardWebRequest(
        @NotBlank String name,
        String description
) {
    public AdminUpdateBoardRequest toCommand(String boardId, String userId) {
        return new AdminUpdateBoardRequest(boardId, name, description, userId);
    }
}
