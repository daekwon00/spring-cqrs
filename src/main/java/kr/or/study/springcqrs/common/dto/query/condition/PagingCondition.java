package kr.or.study.springcqrs.common.dto.query.condition;

import kr.or.study.springcqrs.common.enums.SortOrder;

public record PagingCondition(
        int offset,
        int limit,
        SortOrder sortOrder
) {
}
