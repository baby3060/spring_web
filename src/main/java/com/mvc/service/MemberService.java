package com.mvc.service;

import com.mvc.entity.Member;

public interface MemberService {
    int regist(Member member);
    int count(String email);
    Member selectMember(String email);
    void updatePassword(Member member, String newPassword);
    void accesssTest(Member member) throws RuntimeException;
}