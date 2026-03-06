package kr.or.study.springcqrs.system.code.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.system.code.dto.query.response.CodeGroupResponse;
import kr.or.study.springcqrs.system.code.dto.query.response.CodeResponse;
import kr.or.study.springcqrs.system.code.dto.web.CreateCodeWebRequest;
import kr.or.study.springcqrs.system.code.dto.web.UpdateCodeWebRequest;

import java.util.List;

@Tag(name = "공통코드 관리", description = "공통코드 관리 API")
public interface CodeApi {

    @Operation(summary = "코드그룹 목록 조회")
    ApiResponse<List<CodeGroupResponse>> getCodeGroupList();

    @Operation(summary = "코드 목록 조회")
    ApiResponse<List<CodeResponse>> getCodeList(String groupCode);

    @Operation(summary = "코드 생성")
    ApiResponse<CodeResponse> createCode(CreateCodeWebRequest request);

    @Operation(summary = "코드 수정")
    ApiResponse<CodeResponse> updateCode(String codeId, UpdateCodeWebRequest request);

    @Operation(summary = "코드 삭제")
    ApiResponse<Void> deleteCode(String codeId);
}
