package com.example.springBoards.controller.board;


import com.example.springBoards.dto.board.BoardDTO;
import com.example.springBoards.dto.board.BoardFileDTO;
import com.example.springBoards.service.board.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public String boardListPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model) {
        // 한 페이지당 게시물 수를 5로 설정
        int pageSize = 5;

        // 전체 게시글 수 조회
        int totalBoardCount = boardService.getTotalBoardCount();

        // 총 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalBoardCount / pageSize);

        // 현재 페이지에 해당하는 게시글 목록 조회
        List<BoardDTO> boardDTOList = boardService.boardList(pageNum, pageSize);

        // 모델에 추가
        model.addAttribute("boardList", boardDTOList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNum);

        return "board/board-list";
    }


    @GetMapping("/board/write")
    public String boardWritePage() {
        return "board/board-write";
    }

    @GetMapping("/board/detail/{id}")
    public String boardDetail(@PathVariable("id") Long id, Model model) {
        /*
            1. 조회수 처리
            2. 상세 내용 가져오기
            3. 파일 첨부 확인 조건문 추가
         */
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.boardFindById(id);
        model.addAttribute("board", boardDTO);
        if (boardDTO.getFileAttached() == 1) {
            List<BoardFileDTO> boardFileDTOList = boardService.findFile(id);
            model.addAttribute("boardFileList", boardFileDTOList);
        }
        return "board/board-detail";
    }

    @GetMapping("/board/update/{id}")
    public String boardUpdatePage(@PathVariable("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.boardFindById(id);
        model.addAttribute("board", boardDTO);
        return "board/board-update";
    }


    @PostMapping("/board/save")
    public String boardSave(@ModelAttribute BoardDTO boardDTO, HttpSession session) throws IOException {
        /*
            1. 세션에서 사용자 ID(Long 타입)를 안전하게 가져오기
         */
        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId != null) {
            boardDTO.setMemberId(memberId);
            boardService.boardSave(boardDTO);
            return "redirect:/board/list";
        }
        return "redirect:/board/write";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(BoardDTO boardDTO, Model model) {
        boardService.update(boardDTO);
        BoardDTO dto = boardService.boardFindById(boardDTO.getId());
        model.addAttribute("board", dto);
        return "redirect:/board/list";
    }

    @GetMapping("/board/delete/{id}")
    public String boardDelete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "redirect:/board/list";
    }
}
