package com.mvc.repository.impl;

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
    public int count(String email) {
        MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("email", email);

        return this.getNamedParameterJdbcTemplate()
                   .queryForObject("Select Count(*) As cnt From TBMEMBER Where email = :email"
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

        return this.getNamedParameterJdbcTemplate()
                   .update("Insert Into TBMEMBER(email, name, password, login_type, allow_mail) Values (:email, :name, :password, :login_type, :allow_mail)", param);
    }

    @Override
    public Member selectMember(String email) {
        MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("email", email);

        return this.getNamedParameterJdbcTemplate().queryForObject("Select email, name, password, login_type, allow_mail From TBMEMBER Where email = :email", param, simpleMapper);
    }

    @Override
    public void deleteAll() {
        this.getNamedParameterJdbcTemplate().update("Delete From TBMEMBER", new MapSqlParameterSource());
    }

    @Override
    public long countAll() {
        return this.getNamedParameterJdbcTemplate().queryForObject("Select Count(*) As cnt From TBMEMBER", new MapSqlParameterSource(), Long.class);
    }
}