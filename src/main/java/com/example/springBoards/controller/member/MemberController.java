package com.example.springBoards.controller.member;

import com.example.springBoards.dto.member.MemberDTO;
import com.example.springBoards.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/register")
    public String memberSave(@ModelAttribute  MemberDTO memberDTO) {
        memberService.memberSave(memberDTO);
        return "redirect:/login";
    }

    @PostMapping("/api/member/mypage")
    public String memberLogin(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        boolean loginResult = memberService.memberLogin(memberDTO);
        if (loginResult) {
            MemberDTO dto = memberService.getMemberDetailByEmail(memberDTO.getMemberEmail());
            session.setAttribute("memberName", dto.getMemberName());
            session.setAttribute("memberId", dto.getId());
            return "member/member-mypage";
        } else {
            return "redirect:/login";
        }
    }
}
