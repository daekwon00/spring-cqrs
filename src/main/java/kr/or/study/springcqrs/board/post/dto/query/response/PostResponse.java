package kr.or.study.springcqrs.board.post.dto.query.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "게시글 응답")
public record PostResponse(

        @Schema(description = "게시글 ID")
        Long id,

        @Schema(description = "게시판 ID")
        String boardId,

        @Schema(description = "제목")
        String title,

        @Schema(description = "내용")
        String content,

        @Schema(description = "작성자 정보")
        AuthorResponse author,

        @Schema(description = "조회수")
        int viewCount,

        @Schema(description = "첨부파일 목록")
        List<FileInfoResponse> files,

        @Schema(description = "작성일")
        LocalDateTime createdAt,

        @Schema(description = "수정일")
        LocalDateTime updatedAt
) {

    @Schema(description = "작성자")
    public record AuthorResponse(
            String id,
            String username,
            String name,
            String email,
            String role
    ) {
    }

    @Schema(description = "첨부파일")
    public record FileInfoResponse(
            String id,
            String originalName,
            String storedName,
            long size,
            String contentType
    ) {
    }
}
