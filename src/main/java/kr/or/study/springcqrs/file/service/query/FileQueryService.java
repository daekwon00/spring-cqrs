package kr.or.study.springcqrs.file.service.query;

import kr.or.study.springcqrs.common.exception.NotFoundException;
import kr.or.study.springcqrs.file.mapper.query.FileQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileQueryService {

    private final FileQueryMapper fileQueryMapper;

    public Map<String, Object> getFileById(String fileId) {
        Map<String, Object> file = fileQueryMapper.selectFileById(fileId);
        if (file == null) {
            throw new NotFoundException("파일을 찾을 수 없습니다.");
        }
        return file;
    }
}
