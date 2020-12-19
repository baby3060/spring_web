package com.mvc.repository.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.mvc.entity.Member;
import com.mvc.repository.MemberRepository;

public class MemberRepositoryMB extends SqlSessionDaoSupport implements MemberRepository {
    
    public int count(int memberSeq) {
        return 0;
    }

    public int regist(Member member) {
        return 0;
    }

    public Member selectMember(int memberSeq) {
        return new Member();
    }
    public void deleteAll() {

    }

    public long countAll() {
        return 0L;
    }
    public void updatePasswd(Member member, String newPasswd) {

    }

    public List<Member> listBasic() {
        return new ArrayList<Member>();
    }

    public List<Member> listFromTo(LocalDateTime from, LocalDateTime to) {
        return new ArrayList<Member>();
    }
}
