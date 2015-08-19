package com.javahonk.dao.impl;

import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

import com.javahonk.dao.UserDao;

/**
 * @author CENTAUR
 */
public class UserDaoImpl implements UserDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean isValidUser(String username, String password) throws SQLException {
		String sqlQuery = "SELECT COUNT(1) FROM USER WHERE USERNAME = ? AND PASSWORD = ?";

		Integer count = (Integer) jdbcTemplate.queryForObject(sqlQuery, new Object[]{username, password}, Integer.class);
		if (count != null && count.intValue() > 0) {
			System.out.println("Count:  " + count.intValue()); 
			return true;
		}
		return false;
		
		// PreparedStatement pstmt =
		// dataSource.getConnection().prepareStatement(query);
		// pstmt.setString(1, username);
		// pstmt.setString(2, password);
		// ResultSet resultSet = pstmt.executeQuery();
		// if (resultSet.next())
		// return (resultSet.getInt(1) > 0);
		// else
		// return false;
	}

}