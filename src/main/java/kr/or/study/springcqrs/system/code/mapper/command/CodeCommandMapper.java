package kr.or.study.springcqrs.system.code.mapper.command;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CodeCommandMapper {

    void insertCode(@Param("codeId") String codeId, @Param("codeGroupId") String codeGroupId,
                    @Param("codeName") String codeName, @Param("codeValue") String codeValue,
                    @Param("sortOrder") int sortOrder, @Param("description") String description,
                    @Param("createdBy") String createdBy);

    void updateCode(@Param("codeId") String codeId, @Param("codeGroupId") String codeGroupId,
                    @Param("codeName") String codeName, @Param("codeValue") String codeValue,
                    @Param("sortOrder") int sortOrder, @Param("description") String description,
                    @Param("modifiedBy") String modifiedBy);

    void deleteCode(@Param("codeId") String codeId);
}
