package com.mvc.repository.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.mvc.command.model.BoardSearchVO;
import com.mvc.entity.Member;
import com.mvc.repository.MemberRepository;

public class MemberRepositoryMB extends SqlSessionDaoSupport implements MemberRepository {
    
    public int count(int memberSeq) {
        return 0;
    }
    
	@Override
	public int countByMobile(String mobile) {
		// TODO Auto-generated method stub
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
@Override
public int countByEmail(String email) {
	// TODO Auto-generated method stub
	return 0;
}
    public long countAll() {
        return 0L;
    }
    public void updatePasswd(Member member, String newPasswd) {

    }

    public List<Member> listBasic(BoardSearchVO search) {
        return new ArrayList<Member>();
    }

    public List<Member> listFromTo(LocalDateTime from, LocalDateTime to) {
        return new ArrayList<Member>();
    }
    
    @Override
    public Member selectMemberByMobile(String mobile) {
    	// TODO Auto-generated method stub
    	return null;
    }
    
    @Override
    public int countByEmailPass(String email, String password) {
    	// TODO Auto-generated method stub
    	return 0;
    }
    
    @Override
    public Member selectMemberByEmail(String email) {
    	// TODO Auto-generated method stub
    	return null;
    }
}
