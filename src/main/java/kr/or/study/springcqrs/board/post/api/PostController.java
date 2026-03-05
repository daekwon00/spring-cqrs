package kr.or.study.springcqrs.board.post.api;

import kr.or.study.springcqrs.board.post.dto.query.condition.PostSearch;
import kr.or.study.springcqrs.board.post.dto.query.response.PostResponse;
import kr.or.study.springcqrs.board.post.dto.web.PostWebRequest;
import kr.or.study.springcqrs.board.post.service.query.PostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board/post")
public class PostController implements PostApi{

    private final PostQueryService postQueryService;

    @Override
    @GetMapping
    public List<PostResponse> getPostList(
            @ModelAttribute @Validated PostWebRequest request)
    {
        PostSearch search = request.toSearch();
        return postQueryService.getPostList(search);
    }
}
