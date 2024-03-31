package com.example.springBoards.service.member.memberimpl;

import com.example.springBoards.dto.member.MemberDTO;
import com.example.springBoards.repository.member.MemberRepository;
import com.example.springBoards.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void memberSave(MemberDTO memberDTO) {
        memberRepository.memberSave(memberDTO);
    }

    @Override
    public boolean memberLogin(MemberDTO memberDTO) {
        /*
            1. 아이디 조회해서 있으면 true 반환 아니면 null 반환
         */
        MemberDTO loginMember = memberRepository.memberLogin(memberDTO);
        if (loginMember != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MemberDTO getMemberDetailByEmail(String memberEmail) {
        return memberRepository.memberDetail(memberEmail);
    }
}
