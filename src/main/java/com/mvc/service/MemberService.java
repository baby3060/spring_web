package com.mvc.service;

import java.time.LocalDateTime;
import java.util.List;

import com.mvc.entity.Member;

public interface MemberService {
    int regist(Member member);
    int count(int memberSeq);
    int countByMobile(String mobile);
    int countByEmail(String email);
    int countByEmailPass(String email, String password);
    Member selectMember(int memberSeq);
    Member selectMemberByEmail(String email);
    Member selectMemberByMobile(String mobile);
    void updatePassword(Member member, String newPassword);
    void accesssTest(Member member) throws RuntimeException;
    List<Member> getListBasic(LocalDateTime from, LocalDateTime to);
}