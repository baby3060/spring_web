package com.mvc.service;

import java.time.LocalDateTime;
import java.util.List;

import com.mvc.entity.Member;

public interface MemberService {
    int regist(Member member);
    int count(String email);
    Member selectMember(String email);
    void updatePassword(Member member, String newPassword);
    void accesssTest(Member member) throws RuntimeException;
    List<Member> getListBasic(LocalDateTime from, LocalDateTime to);
}