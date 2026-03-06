package kr.or.study.springcqrs.file.mapper.query;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface FileQueryMapper {

    Map<String, Object> selectFileById(@Param("fileId") String fileId);
}
