package kr.or.study.springcqrs.board.post.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.board.post.dto.query.response.PostListResponse;
import kr.or.study.springcqrs.board.post.dto.query.response.PostResponse;
import kr.or.study.springcqrs.board.post.dto.web.CreatePostWebRequest;
import kr.or.study.springcqrs.board.post.dto.web.PostWebRequest;
import kr.or.study.springcqrs.board.post.dto.web.UpdatePostWebRequest;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.common.dto.response.PageResponse;

import java.util.List;

@Tag(name = "게시글", description = "게시글 API")
public interface PostApi {

    @Operation(summary = "게시글 목록 조회")
    ApiResponse<PageResponse<PostListResponse>> getPostList(String boardId, PostWebRequest request);

    @Operation(summary = "최근 게시글 조회")
    ApiResponse<List<PostListResponse>> getRecentPosts();

    @Operation(summary = "게시글 상세 조회")
    ApiResponse<PostResponse> getPostDetail(Long postId);

    @Operation(summary = "게시글 생성")
    ApiResponse<PostResponse> createPost(CreatePostWebRequest request);

    @Operation(summary = "게시글 수정")
    ApiResponse<PostResponse> updatePost(Long postId, UpdatePostWebRequest request);

    @Operation(summary = "게시글 삭제")
    ApiResponse<Void> deletePost(Long postId);
}
