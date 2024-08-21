package dev;

import java.sql.Connection;

import dev.util.DBUtil;

public class Main {

	public static void main(String[] args) {
	
		
		Connection conn = DBUtil.getConnection();
		System.out.println(conn);

	}

}
