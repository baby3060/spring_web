package com.mvc.repository;

import com.mvc.entity.Member;

public interface MemberRepository {
    int count(String email);
    int regist(Member member);
}