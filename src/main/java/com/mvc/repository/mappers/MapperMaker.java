package com.mvc.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.mvc.entity.Level;
import com.mvc.entity.Member;
import com.mvc.entity.SnsConfig;
import com.mvc.entity.SnsInfo;

/**
 * MemberRepository 인터페이스 구현 클래스에서 사용할(JdbcTemplate 사용) RowMapper 반환용 팩토리 클래스
 */
@Configuration
public class MapperMaker {

	@Bean
	public RowMapper<SnsConfig> snsConfigMapper() {
		return new RowMapper<SnsConfig>() {
			public SnsConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
				SnsConfig snsConfig = new SnsConfig();

				snsConfig.setCode(rs.getString("API_CODE"));
				snsConfig.setApiKey(rs.getString("API_KEY"));
				snsConfig.setApiClient(rs.getString("CLIENT_KEY"));

				return snsConfig;
			}
		};
	}

	@Bean
	public RowMapper<SnsInfo> snsInfoMapper() {
		return new RowMapper<SnsInfo>() {
			public SnsInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				SnsInfo snsInfo = new SnsInfo();

				snsInfo.setMemberSeq(rs.getInt("member_seq"));
				snsInfo.setSnsId(rs.getInt("sns_id"));

				snsInfo.setSnsEmail(rs.getString("sns_email"));
				snsInfo.setSnsGubun(rs.getString("sns_gubun"));
				snsInfo.setSnsMobile(rs.getString("sns_mobile"));
				snsInfo.setSnsName(rs.getString("sns_name"));

				return snsInfo;
			}
		};
	}

	@Bean
	public RowMapper<Member> simpleMapper() {
		return new RowMapper<Member>() {
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member();

				member.setName(rs.getString("name"));
				member.setMemberSeq(rs.getInt("member_seq"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setAllowMail(
						((rs.getString("allow_mail") == null) ? false : rs.getString("allow_mail").equals("T")));
				member.setLoginType(rs.getString("login_type"));
				member.setUserLevel(Level.valueOf(rs.getInt("user_level")));
				member.setRegistDate(rs.getTimestamp("regist_date").toLocalDateTime());
				member.setMobile(rs.getString("mobile"));

				return member;
			}
		};
	}
}