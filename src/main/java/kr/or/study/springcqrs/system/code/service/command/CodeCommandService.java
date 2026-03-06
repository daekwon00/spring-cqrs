package kr.or.study.springcqrs.system.code.service.command;

import kr.or.study.springcqrs.system.code.mapper.command.CodeCommandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CodeCommandService {

    private final CodeCommandMapper codeCommandMapper;

    public void createCode(String codeId, String codeGroupId, String codeName, String codeValue,
                           int sortOrder, String description, String userId) {
        codeCommandMapper.insertCode(codeId, codeGroupId, codeName, codeValue, sortOrder, description, userId);
    }

    public void updateCode(String codeId, String codeGroupId, String codeName, String codeValue,
                           int sortOrder, String description, String userId) {
        codeCommandMapper.updateCode(codeId, codeGroupId, codeName, codeValue, sortOrder, description, userId);
    }

    public void deleteCode(String codeId) {
        codeCommandMapper.deleteCode(codeId);
    }
}
