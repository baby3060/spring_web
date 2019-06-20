package com.mvc.repository.support;

import javax.sql.DataSource;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class DaoSupport extends NamedParameterJdbcDaoSupport {
    // 자동 sequence 반환을 쉽게 하기 위한 jdbcInsert
    protected SimpleJdbcInsert jdbcInsert;

    @Resource
    public void setDs(DataSource dataSource) {
        setDataSource(dataSource);

        this.jdbcInsert = new SimpleJdbcInsert(dataSource);
    }
}