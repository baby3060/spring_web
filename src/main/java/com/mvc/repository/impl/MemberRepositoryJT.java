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

    public int count(String email) {
        MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("email", email);

        return this.getNamedParameterJdbcTemplate()
                   .queryForObject("Select Count(*) As cnt From TBMEMBER Where email = :email"
                                  , param
                                  , Integer.class);
    }

    public int regist(Member member) {
        return 1;
    }
}