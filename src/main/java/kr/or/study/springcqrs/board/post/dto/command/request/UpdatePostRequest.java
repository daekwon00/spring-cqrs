package kr.or.study.springcqrs.board.post.dto.command.request;

public record UpdatePostRequest(
        Long postId,
        String title,
        String content,
        String modifiedBy
) {
}
