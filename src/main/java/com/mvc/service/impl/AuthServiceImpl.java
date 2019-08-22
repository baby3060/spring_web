package com.mvc.service.impl;

import com.mvc.entity.Member;
import com.mvc.entity.UserInfo;
import com.mvc.repository.MemberRepository;
import com.mvc.service.AuthService;
import com.mvc.service.MemberService;

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
    public UserInfo authenticate(String email, String password) {
        Member member = null;

        if(memberService.count(email) == 1) {
            member = memberService.selectMember(email);
        }

        if( member == null ) {
            
        }

        if( isPasswordAuth(member.getPassword(), password) ) {
            
        }

        return new UserInfo(member.getEmail(), member.getName());
    }

    public boolean isPasswordAuth(String memberPass, String password) {
        return true;
    }
}