package com.mvc.service.impl;

import java.time.LocalDateTime;
import java.util.*;

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

    public int count(int memberSeq) {
        return memberRepository.count(memberSeq);
    }

    public Member selectMember(int memberSeq) {
        return memberRepository.selectMember(memberSeq);
    }

    public void updatePassword(Member member, String newPassword) {
        memberRepository.updatePasswd(member, newPassword);
    }

    public List<Member> getListBasic(LocalDateTime from, LocalDateTime to) {
        List<Member> memberList = new ArrayList<Member>();

        if( from != null && to != null ) {
            memberList = memberRepository.listFromTo(from, to);
        } else {
            memberList = memberRepository.listBasic();
        }

        return memberList;
    }

    public void accesssTest(Member member) throws RuntimeException {
        
    }
}