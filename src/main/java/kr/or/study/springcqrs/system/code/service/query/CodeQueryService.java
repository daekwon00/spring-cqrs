package kr.or.study.springcqrs.system.code.service.query;

import kr.or.study.springcqrs.common.exception.NotFoundException;
import kr.or.study.springcqrs.system.code.dto.query.response.CodeGroupResponse;
import kr.or.study.springcqrs.system.code.dto.query.response.CodeResponse;
import kr.or.study.springcqrs.system.code.mapper.query.CodeQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CodeQueryService {

    private final CodeQueryMapper codeQueryMapper;

    public List<CodeGroupResponse> getCodeGroupList() {
        return codeQueryMapper.selectCodeGroupList();
    }

    public List<CodeResponse> getCodeList(String groupCode) {
        return codeQueryMapper.selectCodeList(groupCode);
    }

    public CodeResponse getCodeById(String codeId) {
        CodeResponse code = codeQueryMapper.selectCodeById(codeId);
        if (code == null) {
            throw new NotFoundException("코드를 찾을 수 없습니다.");
        }
        return code;
    }
}
