package kr.or.study.springcqrs.board.writing.dto.query.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public record WritingResponse(

        @Schema(description = "게시글 ID", example = "1")
        Long postId,

        @Schema(description = "게시판 ID", example = "NOTICE")
        String boardId,

        @Schema(description = "제목", example = "공지사항 제목")
        String title,

        @Schema(description = "내용", example = "공지사항 내용")
        String content,

        @Schema(description = "조회수", example = "10")
        Integer viewCount,

        @Schema(description = "공지 여부", example = "true")
        Boolean isNotice,

        @Schema(description = "비밀글 여부", example = "false")
        Boolean isSecret,

        @Schema(description = "작성자", example = "admin")
        String createdBy,

        @Schema(description = "작성일", example = "2026-03-05T10:00:00")
        LocalDateTime createdDate,

        @Schema(description = "수정자", example = "admin")
        String modifiedBy,

        @Schema(description = "수정일", example = "2026-03-05T12:00:00")
        LocalDateTime modifiedDate
) {
}
