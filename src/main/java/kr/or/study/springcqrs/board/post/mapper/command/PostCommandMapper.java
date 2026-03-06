package kr.or.study.springcqrs.board.post.mapper.command;

import kr.or.study.springcqrs.board.post.dto.command.request.CreatePostRequest;
import kr.or.study.springcqrs.board.post.dto.command.request.UpdatePostRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostCommandMapper {

    Long insertPost(CreatePostRequest request);

    int updatePost(UpdatePostRequest request);

    int deletePost(@Param("postId") Long postId);

    int updateViewCount(@Param("postId") Long postId);

    int insertPostFile(@Param("postId") Long postId, @Param("fileId") String fileId);

    int deletePostFiles(@Param("postId") Long postId);
}
