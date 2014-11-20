package core.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ConnectionManager {
	DataSource ds;
	
	public ConnectionManager(DataSource dataSource) {
		this.ds = dataSource;
	}

	public Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
		}
		return null;
	}
}
