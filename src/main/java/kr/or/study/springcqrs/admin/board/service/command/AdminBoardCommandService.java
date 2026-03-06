package kr.or.study.springcqrs.admin.board.service.command;

import kr.or.study.springcqrs.admin.board.dto.command.request.AdminCreateBoardRequest;
import kr.or.study.springcqrs.admin.board.dto.command.request.AdminUpdateBoardRequest;
import kr.or.study.springcqrs.admin.board.mapper.command.AdminBoardCommandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AdminBoardCommandService {

    private final AdminBoardCommandMapper adminBoardCommandMapper;

    public void createBoard(AdminCreateBoardRequest request) {
        adminBoardCommandMapper.insertBoard(request);
    }

    public void updateBoard(AdminUpdateBoardRequest request) {
        adminBoardCommandMapper.updateBoard(request);
    }

    public void toggleActive(String boardId) {
        adminBoardCommandMapper.toggleActive(boardId);
    }
}
