package kr.or.study.springcqrs.board.post.dto.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import kr.or.study.springcqrs.board.post.dto.query.condition.PostSearch;
import kr.or.study.springcqrs.common.enums.SortOrder;

@Schema(description = "게시글 목록 조회 요청")
public record PostWebRequest(

        @Schema(description = "게시판 ID", example = "NOTICE")
        @NotBlank
        String boardId,

        @Schema(description = "페이지 번호", example = "1")
        Integer page,

        @Schema(description = "페이지 사이즈", example = "10")
        Integer pageSize,

        @Schema(description = "정렬 순서", example = "DESC")
        SortOrder sortOrder

) {

    public PostWebRequest {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (sortOrder == null) {
            sortOrder = SortOrder.DESC;
        }
    }

    public PostSearch toSearch() {
        return new PostSearch(
                this.boardId,
                (this.page - 1) * this.pageSize,
                this.pageSize,
                this.sortOrder
        );
    }
}
