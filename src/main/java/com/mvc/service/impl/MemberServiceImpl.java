package com.mvc.service.impl;

import com.mvc.entity.Level;
import com.mvc.entity.Member;
import com.mvc.repository.MemberRepository;
import com.mvc.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public int regist(Member member) {
        if( member.getLevel() == null ) {
            member.setLevel(Level.BRONZE);
        }

        return memberRepository.regist(member);
    }

    public int count(String email) {
        return memberRepository.count(email);
    }

    public Member selectMember(String email) {
        return memberRepository.selectMember(email);
    }

    public void updatePassword(Member member, String newPassword) {
        memberRepository.updatePasswd(member, newPassword);
    }

    public void accesssTest(Member member) throws RuntimeException {
        
    }
}