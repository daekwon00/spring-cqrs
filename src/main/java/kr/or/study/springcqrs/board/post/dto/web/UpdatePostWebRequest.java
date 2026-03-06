package kr.or.study.springcqrs.board.post.dto.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Schema(description = "게시글 수정 요청")
public record UpdatePostWebRequest(

        @Schema(description = "제목")
        @NotBlank(message = "제목을 입력해주세요.")
        String title,

        @Schema(description = "내용")
        String content,

        @Schema(description = "첨부파일 ID 목록")
        List<String> fileIds
) {
}
