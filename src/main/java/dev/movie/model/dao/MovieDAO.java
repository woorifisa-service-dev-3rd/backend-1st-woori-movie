package dev.movie.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.movie.model.dto.MovieTime;
import dev.movie.util.DBUtil;

public class MovieDAO {

	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;

	public static List<String> findAll() {
		final String selectQuery = "SELECT DISTINCT title FROM movie";
		List<String> movies = new ArrayList<>();

		try {
			connection = DBUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectQuery);

			while (resultSet.next()) {
				String title = resultSet.getString("title");
				movies.add(title);
			}

			return movies;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
				} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static List<MovieTime> findAllByTitle(String title) {
		final String selectQuery = "SELECT id, LEFT(TIME(time), 5) AS time FROM movie where time >= now() and title = " + '\'' + title + '\'';
		List<MovieTime> movieTimeList = new ArrayList<>();

		try {
			connection = DBUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                MovieTime movieTime = MovieTime.builder()
                        .id(resultSet.getLong("id"))
                        .time(resultSet.getString("time"))
                        .build();

                movieTimeList.add(movieTime);
            }
            return movieTimeList;
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
				} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}
}
