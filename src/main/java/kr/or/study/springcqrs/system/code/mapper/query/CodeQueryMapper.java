package kr.or.study.springcqrs.system.code.mapper.query;

import kr.or.study.springcqrs.system.code.dto.query.response.CodeGroupResponse;
import kr.or.study.springcqrs.system.code.dto.query.response.CodeResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CodeQueryMapper {

    List<CodeGroupResponse> selectCodeGroupList();

    List<CodeResponse> selectCodeList(@Param("groupCode") String groupCode);

    CodeResponse selectCodeById(@Param("codeId") String codeId);
}
