package com.example.springBoards.service.member;

import com.example.springBoards.dto.member.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    void memberSave(MemberDTO memberDTO);
    boolean memberLogin(MemberDTO memberDTO);
    MemberDTO getMemberDetailByEmail(String memberEmail);
}
