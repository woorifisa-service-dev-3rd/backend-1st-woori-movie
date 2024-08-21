package dev.movie;

import java.sql.Connection;

import dev.movie.service.PayService;
import dev.movie.util.DBUtil;
import dev.service.cloud.Console;

public class Main {

	public static void main(String[] args) {
		
		Connection conn = DBUtil.getConnection();
		System.out.println(conn);

	}

}
