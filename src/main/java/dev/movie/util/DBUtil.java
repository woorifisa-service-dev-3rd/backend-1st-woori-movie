package dev.movie.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import dev.service.cloud.DBConfigurer;

public class DBUtil {
	
	static String argument;
	
	public static void setArgument(String s) {
		argument = s;
	}
	
	public static Connection getConnection() {
		Properties prop = DBConfigurer.readProperties(argument);

		try {
			final String USER_NAME = prop.getProperty("USER_NAME");
			final String PASSWORD = prop.getProperty("PASSWORD");
			final String DB_URL = prop.getProperty("DB_URL");
			
			Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
