package com.todak.todakf.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMemoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired  // properties 에서 가져옴
    public JdbcMemoRepository(DataSource dataSource) {
        // datasource -> properties 파일에서 설정한 DB 관련 정보들이 담김
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
