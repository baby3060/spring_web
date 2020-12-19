package com.mvc.repository.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
                   .queryForObject("Select Count(*) As cnt From TBMEMBER Where memberSeq = :memberSeq"
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

        return this.getNamedParameterJdbcTemplate()
                   .update("Insert Into TBMEMBER(email, name, password, login_type, allow_mail, user_level, regist_date) Values (:email, :name, :password, :login_type, :allow_mail, :userLevel, :registDate)", param);
    }

    @Override
    public Member selectMember(int memberSeq) {
        MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("memberSeq", memberSeq);

        return this.getNamedParameterJdbcTemplate().queryForObject("Select member_seq, email, name, password, login_type, allow_mail, user_level, regist_date From TBMEMBER Where memberSeq = :memberSeq", param, simpleMapper);
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

        this.getNamedParameterJdbcTemplate().update("Update TBMEMBER Set password = :newPasswd Where memberSeq = :memberSeq", param);
    }

    @Override
    public List<Member> listBasic() {
        MapSqlParameterSource param = new MapSqlParameterSource();

        List<Member> memberList = new ArrayList<Member>();

        memberList = this.getNamedParameterJdbcTemplate().query("Select * From TBMEMBER Order By member_seq", param, this.simpleMapper);

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
}