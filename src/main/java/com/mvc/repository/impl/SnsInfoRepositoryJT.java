package com.mvc.repository.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.mvc.entity.SnsInfo;
import com.mvc.repository.SnsInfoRepository;
import com.mvc.repository.support.DaoSupport;

@Repository("snsInfoRepository")
public class SnsInfoRepositoryJT extends DaoSupport implements SnsInfoRepository {
	
	@Resource(name="snsInfoMapper")
    private RowMapper<SnsInfo> snsInfoMapper;
	
	@Override
	public int countByMobile(String mobile, String snsGubun) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		
		param.addValue("snsGubun", snsGubun);
		param.addValue("mobile", mobile);
		
		return this.getNamedParameterJdbcTemplate()
                .queryForObject("Select Count(*) As cnt From sns_info Where sns_gubun = :snsGubun And REPLACE(sns_mobile, '-', '') = :mobile"
                               , param
                               , Integer.class);
	}

	@Override
	public int insertSInfo(SnsInfo snsInfo) {
		MapSqlParameterSource param = new MapSqlParameterSource();
        
        param.addValue("snsEmail", snsInfo.getSnsEmail());
        param.addValue("snsName", snsInfo.getSnsName());
        param.addValue("snsMobile", snsInfo.getSnsMobile());
        param.addValue("snsGubun", snsInfo.getSnsGubun());
        param.addValue("memberSeq", snsInfo.getMemberSeq());
        
        
        return this.getNamedParameterJdbcTemplate()
                   .update("Insert Into SNS_INFO(sns_email, sns_name, sns_mobile, sns_gubun, member_seq) Values (:snsEmail, :snsName, :snsMobile, :snsGubun, :memberSeq)", param);
	}

	@Override
	public SnsInfo selectSInfoByMobile(String mobile, String snsGubun) {
		MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("mobile", mobile);
        param.addValue("snsGubun", snsGubun);

        return this.getNamedParameterJdbcTemplate().queryForObject("Select sns_id, sns_name, sns_email, sns_mobile, member_seq, sns_gubun From sns_info Where REPLACE(sns_mobile, '-', '') = :mobile and sns_gubun = :snsGubun", param, snsInfoMapper);
	}
	
}
