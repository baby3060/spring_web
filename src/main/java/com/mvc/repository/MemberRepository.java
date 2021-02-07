package com.mvc.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.mvc.command.model.BoardSearchVO;
import com.mvc.entity.Member;

public interface MemberRepository {
    int count(int memberSeq);
    int countByMobile(String mobile);
    int countByEmail(String email);
    int countByEmailPass(String email, String password);
    int regist(Member member);
    Member selectMember(int memberSeq);
    Member selectMemberByEmail(String email);
    Member selectMemberByMobile(String mobile);
    void deleteAll();
    long countAll();
    void updatePasswd(Member member, String newPasswd);
    List<Member> listBasic(BoardSearchVO search);
    List<Member> listFromTo(LocalDateTime from, LocalDateTime to);
}