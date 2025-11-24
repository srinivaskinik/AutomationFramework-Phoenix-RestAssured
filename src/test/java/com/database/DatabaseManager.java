package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;

public class DatabaseManager {
	private static final String DB_URL = ConfigManager.getProperty("DB_URL");
	private static final String DB_USERNAME = ConfigManager.getProperty("DB_USERNAME");
	private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
	private volatile static Connection conn; // Any update that happens to this conn variable, all the threads will be
												// aware of it.

	private DatabaseManager() {

	}

	public static void createConnection() throws SQLException {
		if (conn == null) {// First check which all the parallel threads will enter
			synchronized (DatabaseManager.class) {
				if (conn == null) {// Only for the first connection request
					conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					System.out.println(conn);
				}

			}

		}

		System.out.println(conn);
	}

}
