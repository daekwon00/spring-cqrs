package kr.or.study.springcqrs.admin.board.dto.command.request;

public record AdminCreateBoardRequest(
        String boardId,
        String boardName,
        String description,
        String createdBy
) {}
