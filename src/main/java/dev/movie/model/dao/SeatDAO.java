package dev.movie.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dev.movie.model.dto.SeatDTO;
import dev.movie.util.DBUtil;

public class SeatDAO {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	public static List<SeatDTO> findAllSeat(int movie_id) {
		// 조회 SQL
		final String selectQuery = "SELECT * FROM seat WHERE movie_id = ? order by row_id";

		List<SeatDTO> seats = new ArrayList<>();

		// 쿼리 수행 객체 생성 및 쿼리 실행
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(selectQuery);) {

			pstmt.setInt(1, movie_id);

			try (ResultSet rs = pstmt.executeQuery();) {

				while(rs.next()) {
					int id = rs.getInt("id");
					int col = rs.getInt("col");
					int sel_moive_id = rs.getInt("movie_id");
					int row_id = rs.getInt("row_id");
					
					seats.add(new SeatDTO(id, col, sel_moive_id, row_id));
				}
			}
		
			return seats;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean findSeat(int movie_id, int col, String row) {
		// 조회 SQL
		final String selectQuery = "SELECT * FROM seat WHERE movie_id = ? and col = ? and row_id = (select id from seat_row where name = ?)";

		boolean isEmpty = true;

		// 쿼리 수행 객체 생성 및 쿼리 실행
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(selectQuery);) {

			pstmt.setInt(1, movie_id);
			pstmt.setInt(2, col);
			pstmt.setString(3, row);

			try (ResultSet rs = pstmt.executeQuery();) {

				if (rs.next()) {
					isEmpty = false; 
				}
			}
		
			return isEmpty;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isEmpty;
	}
	
	public static boolean insertSeat(int movie_id, int col, String row) {
		// 조회 SQL
		final String selectQuery = "INSERT INTO seat(movie_id, col, row_id) VALUES(?, ?, (select id from seat_row where name = ?))";

		boolean isSuccess = false;

		// 쿼리 수행 객체 생성 및 쿼리 실행
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(selectQuery);) {

			pstmt.setInt(1, movie_id);
			pstmt.setInt(2, col);
			pstmt.setString(3, row);
			
			boolean result = pstmt.execute();
			if(!result) {
				isSuccess = true;
			}
			return isSuccess;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
}
