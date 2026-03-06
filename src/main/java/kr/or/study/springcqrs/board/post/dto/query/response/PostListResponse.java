package kr.or.study.springcqrs.board.post.dto.query.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "게시글 목록 항목")
public record PostListResponse(

        @Schema(description = "게시글 ID")
        Long id,

        @Schema(description = "게시판 ID")
        String boardId,

        @Schema(description = "제목")
        String title,

        @Schema(description = "작성자명")
        String authorName,

        @Schema(description = "조회수")
        int viewCount,

        @Schema(description = "작성일")
        LocalDateTime createdAt,

        @Schema(description = "수정일")
        LocalDateTime updatedAt
) {
}
