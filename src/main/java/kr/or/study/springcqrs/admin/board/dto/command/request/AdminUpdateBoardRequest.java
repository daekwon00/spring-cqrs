package kr.or.study.springcqrs.admin.board.dto.command.request;

public record AdminUpdateBoardRequest(
        String boardId,
        String boardName,
        String description,
        String modifiedBy
) {}
