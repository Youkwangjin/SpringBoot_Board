package com.example.springBoards.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardFileDTO {

    private Long id;
    private Long boardId;
    private String originalFileName;
    private String storedFileName;
}
