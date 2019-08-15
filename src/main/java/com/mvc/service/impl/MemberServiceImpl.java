package com.mvc.service.impl;

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
        System.out.println("It is regist within MemberService");
        System.out.println(member);
        return 1;
    }

    public int count(String email) {
        return 1;
    }
}