package com.mvc.repository.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.mvc.entity.SnsConfig;
import com.mvc.repository.SnsConfigRepository;
import com.mvc.repository.support.DaoSupport;

@Repository("snsConfigRepository")
public class SnsConfigRepositoryJT extends DaoSupport implements SnsConfigRepository {
	
	@Resource(name="snsConfigMapper")
    private RowMapper<SnsConfig> snsConfigMapper;
	
	@Override
	public String getClientId(String code) {
		// TODO Auto-generated method stub
		
		MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("code", code);
        
        
        return this.getNamedParameterJdbcTemplate().queryForObject("Select client_key From tbapiconfig Where api_code = :code", param, String.class);
	}

	@Override
	public String getApiKey(String code) {
		// TODO Auto-generated method stub
		MapSqlParameterSource param = new MapSqlParameterSource();

        param.addValue("code", code);
        
        
        return this.getNamedParameterJdbcTemplate().queryForObject("Select api_key From tbapiconfig Where api_code = :code", param, String.class);
	}

}
