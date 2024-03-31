package com.example.springBoards.service.board;

import com.example.springBoards.dto.board.BoardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {

    void boardSave(BoardDTO boardDTO);

    List<BoardDTO> boardList(BoardDTO boardDTO);

    void updateHits(Long id);

    BoardDTO boardFindById(Long id);

    void update(BoardDTO boardDTO);

    void delete(Long id);
}
