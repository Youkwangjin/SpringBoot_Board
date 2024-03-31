package com.example.springBoards.service.board.boardimpl;

import com.example.springBoards.dto.board.BoardDTO;
import com.example.springBoards.repository.board.BoardRepository;
import com.example.springBoards.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public void boardSave(BoardDTO boardDTO) {
        boardRepository.boardSave(boardDTO);
    }

    @Override
    public List<BoardDTO> boardList(BoardDTO boardDTO) {
        return boardRepository.boardList(boardDTO);
    }

    @Override
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    @Override
    public BoardDTO boardFindById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public void update(BoardDTO boardDTO) {
        boardRepository.boardUpdate(boardDTO);
    }

    @Override
    public void delete(Long id) {
        boardRepository.boardDelete(id);
    }
}
