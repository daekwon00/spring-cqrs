package kr.or.study.springcqrs.file.mapper.command;

import kr.or.study.springcqrs.file.dto.command.request.FileCreateRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileCommandMapper {

    int insertFile(FileCreateRequest request);
}
