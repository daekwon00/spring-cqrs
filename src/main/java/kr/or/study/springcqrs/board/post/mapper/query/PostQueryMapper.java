package kr.or.study.springcqrs.board.post.mapper.query;

import kr.or.study.springcqrs.board.post.dto.query.condition.PostSearch;
import kr.or.study.springcqrs.board.post.dto.query.response.PostListResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostQueryMapper {

    List<PostListResponse> selectPostList(PostSearch search);

    long selectPostCount(PostSearch search);

    Map<String, Object> selectPostById(@Param("postId") Long postId);

    List<Map<String, Object>> selectPostFiles(@Param("postId") Long postId);

    List<PostListResponse> selectRecentPosts();
}
