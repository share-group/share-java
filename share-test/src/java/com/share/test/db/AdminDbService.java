package com.share.test.db;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.share.core.jdbc.AbstractJDBC;

@Component
public class AdminDbService extends AbstractJDBC {
	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
		check();
	}
}