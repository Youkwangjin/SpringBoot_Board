package com.example.springBoards.repository.board;

import com.example.springBoards.dto.board.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    public void boardSave(BoardDTO boardDTO) {
        sqlSessionTemplate.insert("Board.save", boardDTO);
    }

    public List<BoardDTO> boardList(BoardDTO boardDTO) {
        return sqlSessionTemplate.selectList("Board.boardList", boardDTO);
    }

    public void updateHits(Long id) {
        sqlSessionTemplate.update("Board.updateHits", id);
    }

    public BoardDTO findById(Long id) {
        return sqlSessionTemplate.selectOne("Board.detail", id);
    }

    public void boardUpdate(BoardDTO boardDTO) {
        sqlSessionTemplate.update("Board.update", boardDTO);
    }

    public void boardDelete(Long id) {
        sqlSessionTemplate.delete("Board.delete", id);
    }
}
