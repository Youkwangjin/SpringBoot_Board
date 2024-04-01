package com.example.springBoards.service.board.boardimpl;

import com.example.springBoards.dto.board.BoardDTO;
import com.example.springBoards.dto.board.BoardFileDTO;
import com.example.springBoards.repository.board.BoardRepository;
import com.example.springBoards.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public void boardSave(BoardDTO boardDTO) throws IOException {
        if (boardDTO.getBoardFile().get(0).isEmpty()) {
            // 파일이 없는 경우
            boardDTO.setFileAttached(0);
            boardRepository.boardSave(boardDTO);
        } else {
            /*
                1. 파일이 있는 경우 게시글 저장 후 id값 활용을 위해 return 받음
                2. 파일만 따로 가져오고 파일 이름 가져오기
                3. DB 파일 저장용 이름 만들기
                4. BoardFileDTO 세팅 작업, 파일 저장용 폴더 경로 설정과 저장 처리 작업
                5. board_file_table 저장 처리 작업
             */
            boardDTO.setFileAttached(1);
            BoardDTO savedBoard = boardRepository.boardSave(boardDTO);
            for (MultipartFile boardFile : boardDTO.getBoardFile()) {
                String originalFileName = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "-" + originalFileName;
                BoardFileDTO boardFileDTO = new BoardFileDTO();
                boardFileDTO.setOriginalFileName(originalFileName);
                boardFileDTO.setStoredFileName(storedFileName);
                boardFileDTO.setBoardId(savedBoard.getId());
                String savePath = "C:/work/springBoards/src/main/resources/static/spring_img/" + storedFileName;
                boardFile.transferTo(new File(savePath));
                boardRepository.saveFile(boardFileDTO);
            }
        }
    }

    @Override
    public List<BoardFileDTO> findFile(Long id) {
        return boardRepository.findFile(id);
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
