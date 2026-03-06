package kr.or.study.springcqrs.board.board.service.query;

import kr.or.study.springcqrs.board.board.dto.query.response.BoardResponse;
import kr.or.study.springcqrs.board.board.mapper.query.BoardQueryMapper;
import kr.or.study.springcqrs.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardQueryService {

    private final BoardQueryMapper boardQueryMapper;

    public List<BoardResponse> getBoardList() {
        return boardQueryMapper.selectBoardList();
    }

    public BoardResponse getBoardById(String boardId) {
        BoardResponse board = boardQueryMapper.selectBoardById(boardId);
        if (board == null) {
            throw new NotFoundException("게시판을 찾을 수 없습니다.");
        }
        return board;
    }
}
