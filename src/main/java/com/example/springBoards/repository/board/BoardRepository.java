package com.example.springBoards.repository.board;

import com.example.springBoards.dto.board.BoardDTO;
import com.example.springBoards.dto.board.BoardFileDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    public BoardDTO boardSave(BoardDTO boardDTO) {
        sqlSessionTemplate.insert("Board.save", boardDTO);
        return boardDTO;
    }

    public List<BoardDTO> boardList(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        Map<String, Object> params = new HashMap<>();
        params.put("pageSize", pageSize);
        params.put("offset", offset);
        return sqlSessionTemplate.selectList("Board.boardList", params);
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

    public void saveFile(BoardFileDTO boardFileDTO) {
        sqlSessionTemplate.insert("Board.boardFile", boardFileDTO);
    }

    public List<BoardFileDTO> findFile(Long id) {
        return sqlSessionTemplate.selectList("Board.findFile", id);
    }

    public int getTotalBoardCount() {
        return sqlSessionTemplate.selectOne("Board.getTotalBoardCount");
    }
}
