package kr.or.study.springcqrs.board.post.mapper.query;

import kr.or.study.springcqrs.board.post.dto.query.condition.PostSearch;
import kr.or.study.springcqrs.board.post.dto.query.response.PostResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostQueryMapper {

    List<PostResponse> selectPostList(PostSearch search);
}
