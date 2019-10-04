package com.mvc.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.mvc.entity.Member;

public interface MemberRepository {
    int count(String email);
    int regist(Member member);
    Member selectMember(String email);
    void deleteAll();
    long countAll();
    void updatePasswd(Member member, String newPasswd);
    List<Member> listBasic();
    List<Member> listFromTo(LocalDateTime from, LocalDateTime to);
}