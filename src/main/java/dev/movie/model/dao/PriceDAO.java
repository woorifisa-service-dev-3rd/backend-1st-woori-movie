package dev.movie.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.movie.model.dto.RowPrice;
import dev.movie.util.DBUtil;

public class PriceDAO {
	public static List<RowPrice> getPrice() {
		final String selectQuery = "SELECT * FROM seat_row";

		List<RowPrice> prices = new ArrayList<>();
  
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(selectQuery);) {

			try (ResultSet rs = pstmt.executeQuery();) {

				while(rs.next()) {
					long id = rs.getLong("id");
					String row = rs.getString("name");
					long price = rs.getLong("price");
					
					prices.add(RowPrice.builder().id(id).row(row).price(price).build());
				}
			}
		
			return prices;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
