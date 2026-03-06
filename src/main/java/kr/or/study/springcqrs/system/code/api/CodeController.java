package kr.or.study.springcqrs.system.code.api;

import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.config.security.SecurityUtils;
import kr.or.study.springcqrs.system.code.dto.query.response.CodeGroupResponse;
import kr.or.study.springcqrs.system.code.dto.query.response.CodeResponse;
import kr.or.study.springcqrs.system.code.dto.web.CreateCodeWebRequest;
import kr.or.study.springcqrs.system.code.dto.web.UpdateCodeWebRequest;
import kr.or.study.springcqrs.system.code.service.command.CodeCommandService;
import kr.or.study.springcqrs.system.code.service.query.CodeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class CodeController implements CodeApi {

    private final CodeQueryService codeQueryService;
    private final CodeCommandService codeCommandService;

    @Override
    @GetMapping("/code-groups")
    public ApiResponse<List<CodeGroupResponse>> getCodeGroupList() {
        return ApiResponse.ok(codeQueryService.getCodeGroupList());
    }

    @Override
    @GetMapping("/codes")
    public ApiResponse<List<CodeResponse>> getCodeList(
            @RequestParam(required = false) String groupCode
    ) {
        return ApiResponse.ok(codeQueryService.getCodeList(groupCode));
    }

    @Override
    @PostMapping("/codes")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CodeResponse> createCode(@Validated @RequestBody CreateCodeWebRequest request) {
        String userId = SecurityUtils.getCurrentUserId();
        String codeId = request.groupCode() + "_" + request.code();
        codeCommandService.createCode(codeId, request.groupCode(), request.name(),
                request.value(), request.sortOrder(), request.description(), userId);
        return ApiResponse.ok(codeQueryService.getCodeById(codeId));
    }

    @Override
    @PutMapping("/codes/{codeId}")
    public ApiResponse<CodeResponse> updateCode(
            @PathVariable String codeId,
            @Validated @RequestBody UpdateCodeWebRequest request
    ) {
        String userId = SecurityUtils.getCurrentUserId();
        codeCommandService.updateCode(codeId, request.groupCode(), request.name(),
                request.value(), request.sortOrder(), request.description(), userId);
        return ApiResponse.ok(codeQueryService.getCodeById(codeId));
    }

    @Override
    @DeleteMapping("/codes/{codeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deleteCode(@PathVariable String codeId) {
        codeCommandService.deleteCode(codeId);
        return ApiResponse.ok(null);
    }
}
