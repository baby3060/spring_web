package com.mvc.repository.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.mvc.command.model.BoardSearchVO;
import com.mvc.entity.Member;
import com.mvc.repository.MemberRepository;
import com.mvc.repository.support.DaoSupport;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository("memberRepository")
public class MemberRepositoryJT extends DaoSupport implements MemberRepository {
    
    @Resource(name="simpleMapper")
    private RowMapper<Member> simpleMapper;

    @Override
    public int count(int memberSeq) {
        MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("memberSeq", memberSeq);

        return this.getNamedParameterJdbcTemplate()
                   .queryForObject("Select Count(*) As cnt From TBMEMBER Where member_seq = :memberSeq"
                                  , param
                                  , Integer.class);
    }
    
    @Override
    public int countByEmail(String email) {
    	MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("email", email);
    	
    	// TODO Auto-generated method stub
        return this.getNamedParameterJdbcTemplate()
                .queryForObject("Select Count(*) As cnt From TBMEMBER Where email = :email"
                               , param
                               , Integer.class);
    }
    
    @Override
    public int countByMobile(String mobile) {
    	MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("mobile", mobile.replaceAll("-", ""));
    	
    	// TODO Auto-generated method stub
        return this.getNamedParameterJdbcTemplate()
                .queryForObject("Select Count(*) As cnt From TBMEMBER Where REPLACE(mobile, '-', '') = :mobile"
                               , param
                               , Integer.class);
    }
    
    @Override
    public int countByEmailPass(String email, String password) {
    	MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("email", email);
        param.addValue("password", password);
    	
    	// TODO Auto-generated method stub
        return this.getNamedParameterJdbcTemplate()
                .queryForObject("Select Count(*) As cnt From TBMEMBER Where email = :email and password = :password"
                               , param
                               , Integer.class);
    }
    
    @Override
    public int regist(Member member) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        
        param.addValue("email", member.getEmail());
        param.addValue("name", member.getName());
        param.addValue("password", member.getPassword());
        param.addValue("login_type", member.getLoginType());
        param.addValue("allow_mail", member.getAllowMail()?"T":"F");
        param.addValue("userLevel", member.getUserLevel().getValue());
        param.addValue("registDate", Timestamp.valueOf(LocalDateTime.now()));
        param.addValue("mobile", member.getMobile());
        
        
        return this.getNamedParameterJdbcTemplate()
                   .update("Insert Into TBMEMBER(email, name, password, login_type, allow_mail, user_level, regist_date, mobile) Values (:email, :name, :password, :login_type, :allow_mail, :userLevel, :registDate, :mobile)", param);
    }

    @Override
    public Member selectMember(int memberSeq) {
        MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("memberSeq", memberSeq);
        
        return this.getNamedParameterJdbcTemplate().queryForObject("Select member_seq, email, name, password, login_type, allow_mail, user_level, regist_date, mobile From TBMEMBER Where member_seq = :memberSeq", param, simpleMapper);
    }
    
    @Override
    public Member selectMemberByEmail(String email) {
    	MapSqlParameterSource param = new MapSqlParameterSource();

    	param.addValue("email", email);
        
        return this.getNamedParameterJdbcTemplate().queryForObject("Select member_seq, email, name, password, login_type, allow_mail, user_level, regist_date, mobile From TBMEMBER Where email = :email", param, simpleMapper);
    }
    
    @Override
    public void deleteAll() {
        this.getNamedParameterJdbcTemplate().update("Delete From TBMEMBER", new MapSqlParameterSource());
    }

    @Override
    public long countAll() {
        return this.getNamedParameterJdbcTemplate().queryForObject("Select Count(*) As cnt From TBMEMBER", new MapSqlParameterSource(), Long.class);
    }

    @Override
    public void updatePasswd(Member member, String newPasswd) {
        MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("newPasswd", newPasswd);
        param.addValue("memberSeq", member.getMemberSeq());

        this.getNamedParameterJdbcTemplate().update("Update TBMEMBER Set password = :newPasswd Where member_seq = :memberSeq", param);
    }

    @Override
    public List<Member> listBasic(BoardSearchVO search) {
        MapSqlParameterSource param = new MapSqlParameterSource();

        List<Member> memberList = new ArrayList<Member>();
        
        param.addValue("start", search.getStart());
        param.addValue("end", search.getLength());
        
        memberList = this.getNamedParameterJdbcTemplate().query("Select * From TBMEMBER Order By member_seq limit :start, :end", param, this.simpleMapper);

        return memberList;
    }

    @Override
    public List<Member> listFromTo(LocalDateTime from, LocalDateTime to) {
        MapSqlParameterSource param = new MapSqlParameterSource();

        List<Member> memberList = new ArrayList<Member>();

        param.addValue("from", from.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        param.addValue("to", to.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        memberList = this.getNamedParameterJdbcTemplate().query("Select * From TBMEMBER Where Date_Format(regist_date, '%Y%m%d') Between :from And :to Order By regist_date", param, this.simpleMapper);

        return memberList;
    }
    
    @Override
    public Member selectMemberByMobile(String mobile) {
    	MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("mobile", mobile);

        return this.getNamedParameterJdbcTemplate().queryForObject("Select member_seq, email, name, password, login_type, allow_mail, user_level, regist_date, mobile From TBMEMBER Where REPLACE(mobile, '-', '') = :mobile", param, simpleMapper);
    }
    
}