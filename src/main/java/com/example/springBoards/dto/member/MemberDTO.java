package com.example.springBoards.dto.member;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberGender;
    private String memberName;
    private int memberAge;
    private String memberCreate;

}
