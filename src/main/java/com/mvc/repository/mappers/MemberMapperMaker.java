package com.mvc.repository.mappers;



import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.mvc.entity.Member;

/**
 * MemberRepository 인터페이스 구현 클래스에서 사용할(JdbcTemplate 사용) RowMapper 반환용 팩토리 클래스
 */
@Configuration
public class MemberMapperMaker {
    
    @Bean
    public RowMapper<Member> simpleMapper() {
        return new RowMapper<Member>() {
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPassword(rs.getString("password"));
                member.setAllowMail(rs.getString("allow_mail").equals("T"));
                member.setLoginType(rs.getString("login_type"));

                return member;
            }
        };
    } 
}