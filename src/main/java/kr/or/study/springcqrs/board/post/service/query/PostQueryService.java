package kr.or.study.springcqrs.board.post.service.query;

import kr.or.study.springcqrs.board.post.dto.query.condition.PostSearch;
import kr.or.study.springcqrs.board.post.dto.query.response.PostResponse;
import kr.or.study.springcqrs.board.post.mapper.query.PostQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQueryService {

    private final PostQueryMapper postQueryMapper;

    public List<PostResponse> getPostList(PostSearch search) {
        return postQueryMapper.selectPostList(search);
    }
}
