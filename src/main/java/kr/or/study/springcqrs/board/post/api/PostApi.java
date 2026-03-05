package kr.or.study.springcqrs.board.post.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.board.post.dto.query.response.PostResponse;
import kr.or.study.springcqrs.board.post.dto.web.PostWebRequest;
import kr.or.study.springcqrs.board.post.dto.web.ViewCountWebRequest;

import java.util.List;

@Tag(name = "게시글", description = "게시글 목록 API")
public interface PostApi {

    @Operation(summary = "게시글 목록 조회")
    List<PostResponse> getPostList(PostWebRequest request);

    @Operation(summary = "게시글 조회수 증가")
    void increaseViewCount(ViewCountWebRequest request);
}
