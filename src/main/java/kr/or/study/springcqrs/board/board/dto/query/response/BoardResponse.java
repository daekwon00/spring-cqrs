package kr.or.study.springcqrs.board.board.dto.query.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시판 응답")
public record BoardResponse(

        @Schema(description = "게시판 ID", example = "NOTICE")
        String id,

        @Schema(description = "게시판명", example = "공지사항")
        String name,

        @Schema(description = "설명")
        String description,

        @Schema(description = "활성 여부")
        boolean isActive,

        @Schema(description = "게시글 수")
        int postCount
) {
}
