package com.mvc.service.impl;

import com.mvc.entity.Member;
import com.mvc.entity.UserInfo;
import com.mvc.repository.MemberRepository;
import com.mvc.service.AuthService;
import com.mvc.service.MemberService;
import com.mvc.service.exceptions.IDNotMatchingException;
import com.mvc.service.exceptions.NotMatchingMemberException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthServiceImpl implements AuthService {
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserInfo authenticate(int memberSeq, String password) {
        Member member = null;

        if(memberService.count(memberSeq) == 1) {
            member = memberService.selectMember(memberSeq);
        }

        if( member == null ) {
            throw new NotMatchingMemberException();
        }

        if( isPasswordAuth(member.getPassword(), password) ) {
            return new UserInfo(member.getMemberSeq(), member.getEmail(), member.getName());    
        } else {
            throw new IDNotMatchingException();
        }
    }

    // 아이디와 패스워드 일치하는가?
    // 암호화 적용하면 됨
    public boolean isPasswordAuth(String memberPass, String password) {
        return memberPass.equals(password);
    }
}