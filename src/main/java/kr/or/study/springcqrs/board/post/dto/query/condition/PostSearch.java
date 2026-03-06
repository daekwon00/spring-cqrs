package kr.or.study.springcqrs.board.post.dto.query.condition;

import kr.or.study.springcqrs.common.enums.SortOrder;

public record PostSearch(
        String boardId,
        int offset,
        int limit,
        SortOrder sortOrder,
        String search,
        String searchType
) {
}
