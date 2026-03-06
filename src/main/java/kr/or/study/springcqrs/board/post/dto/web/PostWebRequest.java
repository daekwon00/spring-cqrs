package kr.or.study.springcqrs.board.post.dto.web;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.or.study.springcqrs.board.post.dto.query.condition.PostSearch;
import kr.or.study.springcqrs.common.enums.SortOrder;

@Schema(description = "게시글 목록 조회 요청")
public record PostWebRequest(

        @Schema(description = "페이지 번호 (0-based)", example = "0")
        Integer page,

        @Schema(description = "페이지 사이즈", example = "10")
        Integer size,

        @Schema(description = "검색어")
        String search,

        @Schema(description = "검색 타입 (title, author, all)")
        String searchType,

        @Schema(description = "정렬 순서", example = "DESC")
        SortOrder sortOrder
) {

    public PostWebRequest {
        if (page == null) page = 0;
        if (size == null) size = 10;
        if (sortOrder == null) sortOrder = SortOrder.DESC;
        if (searchType == null) searchType = "all";
    }

    public PostSearch toSearch(String boardId) {
        return new PostSearch(
                boardId,
                page * size,
                size,
                sortOrder,
                search,
                searchType
        );
    }
}
