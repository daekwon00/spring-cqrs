package kr.or.study.springcqrs.admin.board.service.query;

import kr.or.study.springcqrs.admin.board.dto.query.response.AdminBoardResponse;
import kr.or.study.springcqrs.admin.board.mapper.query.AdminBoardQueryMapper;
import kr.or.study.springcqrs.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminBoardQueryService {

    private final AdminBoardQueryMapper adminBoardQueryMapper;

    public List<AdminBoardResponse> getBoardList() {
        return adminBoardQueryMapper.selectBoardList();
    }

    public AdminBoardResponse getBoardById(String boardId) {
        AdminBoardResponse board = adminBoardQueryMapper.selectBoardById(boardId);
        if (board == null) {
            throw new NotFoundException("게시판을 찾을 수 없습니다.");
        }
        return board;
    }
}
