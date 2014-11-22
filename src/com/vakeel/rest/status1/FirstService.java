package com.vakeel.rest.status1;

import static com.vakeel.rest.constant.ApiConstant.Driver_NAME;
import static com.vakeel.rest.constant.ApiConstant.PASSWORD;
import static com.vakeel.rest.constant.ApiConstant.URL;
import static com.vakeel.rest.constant.ApiConstant.USER_NAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vakeel.rest.dao.OracleDao;

@Path("/first/app")
public class FirstService {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String myName() {
		return "My Name is Vakeel";
	}

	@Path("/hello")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String lastName() {
		return "My Name is mohd";
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String databasetry() {
		Connection conn=null;
		PreparedStatement stmnt=null;
		ResultSet result=null;
		String sql = "SELECT * FROM RESTAPI_TEST";
		String temp=null;
		try {

			Class.forName(Driver_NAME);
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			conn = OracleDao.getOracleConn();
			stmnt = conn.prepareStatement(sql);
			result = stmnt.executeQuery();
			
			
			while (result.next()) {
				System.out.println(result);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}
}
