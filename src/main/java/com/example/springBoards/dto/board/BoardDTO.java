package com.example.springBoards.dto.board;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long id;
    private Long memberId;
    private String boardTitle;
    private String boardWriter;
    private String boardPassword;
    private String boardContents;
    private int boardHits;
    private String boardCreated;
    // 파일 첨부 필드 추가
    private int fileAttached;
    private List<MultipartFile> boardFile;
}
