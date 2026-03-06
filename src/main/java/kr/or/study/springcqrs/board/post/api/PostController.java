package kr.or.study.springcqrs.board.post.api;

import kr.or.study.springcqrs.board.post.dto.command.request.CreatePostRequest;
import kr.or.study.springcqrs.board.post.dto.command.request.UpdatePostRequest;
import kr.or.study.springcqrs.board.post.dto.query.condition.PostSearch;
import kr.or.study.springcqrs.board.post.dto.query.response.PostListResponse;
import kr.or.study.springcqrs.board.post.dto.query.response.PostResponse;
import kr.or.study.springcqrs.board.post.dto.web.CreatePostWebRequest;
import kr.or.study.springcqrs.board.post.dto.web.PostWebRequest;
import kr.or.study.springcqrs.board.post.dto.web.UpdatePostWebRequest;
import kr.or.study.springcqrs.board.post.service.command.PostCommandService;
import kr.or.study.springcqrs.board.post.service.query.PostQueryService;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.common.dto.response.PageResponse;
import kr.or.study.springcqrs.config.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController implements PostApi {

    private final PostQueryService postQueryService;
    private final PostCommandService postCommandService;

    @Override
    @GetMapping("/api/v1/boards/{boardId}/posts")
    public ApiResponse<PageResponse<PostListResponse>> getPostList(
            @PathVariable String boardId,
            @ModelAttribute PostWebRequest request
    ) {
        PostSearch search = request.toSearch(boardId);
        List<PostListResponse> content = postQueryService.getPostList(search);
        long totalElements = postQueryService.getPostCount(search);
        return ApiResponse.ok(PageResponse.of(content, totalElements, request.page(), request.size()));
    }

    @Override
    @GetMapping("/api/v1/posts/recent")
    public ApiResponse<List<PostListResponse>> getRecentPosts() {
        return ApiResponse.ok(postQueryService.getRecentPosts());
    }

    @Override
    @GetMapping("/api/v1/posts/{postId}")
    public ApiResponse<PostResponse> getPostDetail(@PathVariable Long postId) {
        postCommandService.increaseViewCount(postId);
        return ApiResponse.ok(postQueryService.getPostDetail(postId));
    }

    @Override
    @PostMapping("/api/v1/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PostResponse> createPost(
            @RequestBody @Validated CreatePostWebRequest request
    ) {
        String userId = SecurityUtils.getCurrentUserId();
        CreatePostRequest command = new CreatePostRequest(
                request.boardId(), request.title(), request.content(), userId);
        PostResponse response = postCommandService.createPost(command, request.fileIds());
        return ApiResponse.ok(response);
    }

    @Override
    @PutMapping("/api/v1/posts/{postId}")
    public ApiResponse<PostResponse> updatePost(
            @PathVariable Long postId,
            @RequestBody @Validated UpdatePostWebRequest request
    ) {
        String userId = SecurityUtils.getCurrentUserId();
        UpdatePostRequest command = new UpdatePostRequest(
                postId, request.title(), request.content(), userId);
        PostResponse response = postCommandService.updatePost(postId, command, request.fileIds());
        return ApiResponse.ok(response);
    }

    @Override
    @DeleteMapping("/api/v1/posts/{postId}")
    public ApiResponse<Void> deletePost(@PathVariable Long postId) {
        postCommandService.deletePost(postId);
        return ApiResponse.ok(null);
    }
}
