package kr.or.study.springcqrs.board.post.api;

import kr.or.study.springcqrs.board.post.dto.command.request.ViewCountRequest;
import kr.or.study.springcqrs.board.post.dto.query.condition.PostSearch;
import kr.or.study.springcqrs.board.post.dto.query.response.PostResponse;
import kr.or.study.springcqrs.board.post.dto.web.PostWebRequest;
import kr.or.study.springcqrs.board.post.dto.web.ViewCountWebRequest;
import kr.or.study.springcqrs.board.post.service.command.PostCommandService;
import kr.or.study.springcqrs.board.post.service.query.PostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board/post")
public class PostController implements PostApi{

    private final PostQueryService postQueryService;
    private final PostCommandService postCommandService;

    @Override
    @GetMapping
    public List<PostResponse> getPostList(
            @ModelAttribute @Validated PostWebRequest request)
    {
        PostSearch search = request.toSearch();
        return postQueryService.getPostList(search);
    }

    @Override
    @PostMapping("/view-count")
    public void increaseViewCount(
            @RequestBody @Validated ViewCountWebRequest request
    ) {
        ViewCountRequest command = request.toCommand();
        postCommandService.increaseViewCount(command);
    }
}
