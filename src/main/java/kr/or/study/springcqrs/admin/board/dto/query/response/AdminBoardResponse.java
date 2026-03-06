package kr.or.study.springcqrs.admin.board.dto.query.response;

public record AdminBoardResponse(
        String id,
        String name,
        String description,
        boolean isActive,
        long postCount
) {}
