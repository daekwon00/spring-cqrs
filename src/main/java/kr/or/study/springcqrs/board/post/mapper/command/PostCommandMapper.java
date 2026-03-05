package kr.or.study.springcqrs.board.post.mapper.command;

import kr.or.study.springcqrs.board.post.dto.command.request.ViewCountRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostCommandMapper {

    int updateViewCount(ViewCountRequest request);
}
