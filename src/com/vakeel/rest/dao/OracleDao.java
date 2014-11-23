package com.vakeel.rest.dao;

import static com.vakeel.rest.constant.ApiConstant.Driver_NAME;
import static com.vakeel.rest.constant.ApiConstant.PASSWORD;
import static com.vakeel.rest.constant.ApiConstant.URL;
import static com.vakeel.rest.constant.ApiConstant.USER_NAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDao {

	private static Connection conn = null;

	public static Connection getOracleConn() throws SQLException,
			ClassNotFoundException {

		Class.forName(Driver_NAME);
		conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

		return conn;
	}
}
