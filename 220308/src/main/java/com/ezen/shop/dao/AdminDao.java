package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class AdminDao {
	private JdbcTemplate tpl;
	
	@Autowired
	public AdminDao(ComboPooledDataSource ds) {
		this.tpl = new JdbcTemplate(ds);
	}

	public int workerCheck(String wid, String wpwd) {
		int result = 0;
		String sql = "SELECT pwd FROM worker WHERE id=?";
		List<String> list = tpl.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String getPwd = rs.getString("pwd");
				return getPwd;
			}
			
		}, wid);
		if(list.size() == 0) {
			result = -1;
		}else if(wpwd.equals(list.get(0))){
			result = 1;
		}else {
			result = 0;
		}
		
		return result;
	}
	
	
}
