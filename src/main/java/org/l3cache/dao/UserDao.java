package org.l3cache.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.l3cache.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class UserDao extends JdbcDaoSupport{
	
	public void create(User user) {

	}

	public User findById(String userId) {
		String sql = "SELECT * FROM L3_USERS WHERE email = ?";
		RowMapper<User> rowMapper = new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new User(
						rs.getInt("userId"),
						rs.getString("email"),
						rs.getString("password"));
			}
		};
		
		try {
			return getJdbcTemplate().queryForObject(sql, rowMapper, userId);
		}catch (EmptyResultDataAccessException e){
			return null;
		}	
	}
	
	
}
