package kr.or.study.springcqrs.board.post.service.query;

import kr.or.study.springcqrs.board.post.dto.query.condition.PostSearch;
import kr.or.study.springcqrs.board.post.dto.query.response.PostListResponse;
import kr.or.study.springcqrs.board.post.dto.query.response.PostResponse;
import kr.or.study.springcqrs.board.post.mapper.query.PostQueryMapper;
import kr.or.study.springcqrs.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQueryService {

    private final PostQueryMapper postQueryMapper;

    public List<PostListResponse> getPostList(PostSearch search) {
        return postQueryMapper.selectPostList(search);
    }

    public long getPostCount(PostSearch search) {
        return postQueryMapper.selectPostCount(search);
    }

    public PostResponse getPostDetail(Long postId) {
        Map<String, Object> row = postQueryMapper.selectPostById(postId);
        if (row == null) {
            throw new NotFoundException("게시글을 찾을 수 없습니다.");
        }

        List<Map<String, Object>> fileRows = postQueryMapper.selectPostFiles(postId);

        // role 판별
        String authorId = (String) row.get("author_id");
        String role = "USER";

        PostResponse.AuthorResponse author = new PostResponse.AuthorResponse(
                authorId,
                authorId,
                (String) row.get("author_name"),
                (String) row.get("author_email"),
                role
        );

        List<PostResponse.FileInfoResponse> files = fileRows.stream()
                .map(f -> new PostResponse.FileInfoResponse(
                        (String) f.get("id"),
                        (String) f.get("original_name"),
                        (String) f.get("stored_name"),
                        f.get("size") != null ? ((Number) f.get("size")).longValue() : 0,
                        (String) f.get("content_type")
                ))
                .toList();

        return new PostResponse(
                ((Number) row.get("id")).longValue(),
                (String) row.get("board_id"),
                (String) row.get("title"),
                (String) row.get("content"),
                author,
                ((Number) row.get("view_count")).intValue(),
                files,
                toLocalDateTime(row.get("created_date")),
                toLocalDateTime(row.get("modified_date"))
        );
    }

    private LocalDateTime toLocalDateTime(Object value) {
        if (value instanceof LocalDateTime ldt) return ldt;
        if (value instanceof Timestamp ts) return ts.toLocalDateTime();
        return null;
    }

    public List<PostListResponse> getRecentPosts() {
        return postQueryMapper.selectRecentPosts();
    }
}
