package kr.or.study.springcqrs.board.post.dto.command.request;

public record CreatePostRequest(
        String boardId,
        String title,
        String content,
        String createdBy
) {
}
