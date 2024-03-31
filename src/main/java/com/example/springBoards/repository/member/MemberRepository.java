package com.example.springBoards.repository.member;

import com.example.springBoards.dto.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    public void memberSave(MemberDTO memberDTO) {
        sqlSessionTemplate.insert("Member.save", memberDTO);
    }

    public MemberDTO memberLogin(MemberDTO memberDTO) {
        return sqlSessionTemplate.selectOne("Member.login", memberDTO);
    }

    public MemberDTO memberDetail(String memberEmail) {
        return sqlSessionTemplate.selectOne("Member.Detail", memberEmail);
    }
}
