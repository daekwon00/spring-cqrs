package kr.or.study.springcqrs.board.post.service.command;

import kr.or.study.springcqrs.board.post.dto.command.request.CreatePostRequest;
import kr.or.study.springcqrs.board.post.dto.command.request.UpdatePostRequest;
import kr.or.study.springcqrs.board.post.dto.query.response.PostResponse;
import kr.or.study.springcqrs.board.post.mapper.command.PostCommandMapper;
import kr.or.study.springcqrs.board.post.service.query.PostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PostCommandService {

    private final PostCommandMapper postCommandMapper;
    private final PostQueryService postQueryService;

    public PostResponse createPost(CreatePostRequest request, List<String> fileIds) {
        Long postId = postCommandMapper.insertPost(request);

        if (fileIds != null && !fileIds.isEmpty()) {
            for (String fileId : fileIds) {
                postCommandMapper.insertPostFile(postId, fileId);
            }
        }

        return postQueryService.getPostDetail(postId);
    }

    public PostResponse updatePost(Long postId, UpdatePostRequest request, List<String> fileIds) {
        postCommandMapper.updatePost(request);

        postCommandMapper.deletePostFiles(postId);
        if (fileIds != null && !fileIds.isEmpty()) {
            for (String fileId : fileIds) {
                postCommandMapper.insertPostFile(postId, fileId);
            }
        }

        return postQueryService.getPostDetail(postId);
    }

    public void deletePost(Long postId) {
        postCommandMapper.deletePostFiles(postId);
        postCommandMapper.deletePost(postId);
    }

    public void increaseViewCount(Long postId) {
        postCommandMapper.updateViewCount(postId);
    }
}
