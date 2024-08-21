package dev.movie;

import java.sql.Connection;
import dev.movie.util.DBUtil;

public class Main {

	public static void main(String[] args) {
		
		Connection conn = DBUtil.getConnection();
		System.out.println(conn);
	}

}
