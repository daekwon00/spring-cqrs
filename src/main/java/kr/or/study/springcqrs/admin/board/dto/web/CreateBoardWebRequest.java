package kr.or.study.springcqrs.admin.board.dto.web;

import jakarta.validation.constraints.NotBlank;
import kr.or.study.springcqrs.admin.board.dto.command.request.AdminCreateBoardRequest;

public record CreateBoardWebRequest(
        @NotBlank String id,
        @NotBlank String name,
        String description
) {
    public AdminCreateBoardRequest toCommand(String userId) {
        return new AdminCreateBoardRequest(id, name, description, userId);
    }
}
