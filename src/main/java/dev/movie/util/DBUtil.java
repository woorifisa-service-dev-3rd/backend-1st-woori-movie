package dev.movie.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
	public static Connection getConnection() {
		Properties prop = new Properties();

		try {
			FileInputStream fs = new FileInputStream("src/main/resources/jdbc.properties");
			prop.load(fs);
			
			final String USER_NAME = prop.getProperty("USER_NAME");
			final String PASSWORD = prop.getProperty("PASSWORD");
			final String DB_URL = prop.getProperty("DB_URL");
			
			Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			return connection;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
