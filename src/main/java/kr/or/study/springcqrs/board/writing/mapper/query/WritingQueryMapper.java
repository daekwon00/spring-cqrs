package kr.or.study.springcqrs.board.writing.mapper.query;

import kr.or.study.springcqrs.board.writing.dto.query.condition.WritingSearch;
import kr.or.study.springcqrs.board.writing.dto.query.response.WritingResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WritingQueryMapper {

    List<WritingResponse> selectPostList(WritingSearch search);
}
