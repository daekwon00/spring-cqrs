package kr.or.study.springcqrs.board.post.dto.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import kr.or.study.springcqrs.board.post.dto.command.request.ViewCountRequest;

@Schema(description = "조회수 증가 요청")
public record ViewCountWebRequest(

        @Schema(description = "게시글 ID", example = "1")
        @NotNull
        Long postId

) {

    public ViewCountRequest toCommand() {
        return new ViewCountRequest(this.postId);
    }
}
