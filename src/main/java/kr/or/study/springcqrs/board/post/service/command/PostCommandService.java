package kr.or.study.springcqrs.board.post.service.command;

import kr.or.study.springcqrs.board.post.dto.command.request.ViewCountRequest;
import kr.or.study.springcqrs.board.post.mapper.command.PostCommandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PostCommandService {

    private final PostCommandMapper postCommandMapper;

    public void increaseViewCount(ViewCountRequest request) {
        postCommandMapper.updateViewCount(request);
    }
}
