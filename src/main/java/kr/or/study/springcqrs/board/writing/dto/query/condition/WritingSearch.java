package kr.or.study.springcqrs.board.writing.dto.query.condition;

import kr.or.study.springcqrs.common.enums.SortOrder;

public record WritingSearch(
        String boardId,
        int offset,
        int limit,
        SortOrder sortOrder
) {
}
