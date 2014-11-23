package com.vakeel.rest.dao;

import static com.vakeel.rest.constant.ApiConstant.QUERY_ALL_RECORD;
import static com.vakeel.rest.constant.ApiConstant.QUERY_DELETE;
import static com.vakeel.rest.constant.ApiConstant.QUERY_INSERT_RECORD;
import static com.vakeel.rest.constant.ApiConstant.QUERY_MOBILE;
import static com.vakeel.rest.constant.ApiConstant.QUERY_UPDATE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.owasp.esapi.errors.EncodingException;

import com.vakeel.rest.JSON.ToJSON;

public class QueryResult extends OracleDao {

	public static JSONArray getMobileRecord(String mobileNumber)
			throws ClassNotFoundException, SQLException, EncodingException,
			JSONException {

		PreparedStatement stmnt = null;
		ResultSet result = null;
		Connection conn = null;

		ToJSON objConvert = new ToJSON();
		JSONArray jsArray = new JSONArray();

		conn = OracleDao.getOracleConn();
		stmnt = conn.prepareStatement(QUERY_MOBILE);
		stmnt.setString(1, mobileNumber);
		result = stmnt.executeQuery();
		jsArray = objConvert.toJSonArray(result);

		return jsArray;
	}

	public static JSONArray getAllRecords() throws ClassNotFoundException,
			SQLException, EncodingException, JSONException {

		PreparedStatement stmnt = null;
		ResultSet result = null;
		Connection conn = null;

		ToJSON objConvert = new ToJSON();
		JSONArray jsArray = new JSONArray();

		conn = OracleDao.getOracleConn();
		stmnt = conn.prepareStatement(QUERY_ALL_RECORD);

		result = stmnt.executeQuery();

		jsArray = objConvert.toJSonArray(result);
		conn.close();
		return jsArray;
	}

	public int insertIntoPC_PARTS(String NAME, String INTEREST,
			String DEVELOPER, String CTC, String HOMETOWN, String COMPANY,
			String POSITION, String TECHNOLOGY, String PHONENUMBER)
			throws Exception {

		PreparedStatement query = null;
		Connection conn = null;

		try {
			conn = getOracleConn();
			query = conn.prepareStatement(QUERY_INSERT_RECORD);

			query.setString(1, NAME);
			query.setString(2, INTEREST);
			query.setString(3, DEVELOPER);
			query.setString(4, CTC);
			query.setString(5, HOMETOWN);
			query.setString(6, COMPANY);
			query.setString(7, POSITION);
			query.setString(8, TECHNOLOGY);
			query.setString(9, PHONENUMBER);

			query.executeUpdate(); // note the new command for insert statement
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
			return 500; // if a error occurs, return a 500
		} finally {
			if (conn != null)
				conn.close();
		}

		return 200;
	}

	public int updateItem(String companyName, String mobileNumber)
			throws Exception {

		PreparedStatement query = null;
		Connection conn = null;

		try {
			conn = getOracleConn();
			query = conn.prepareStatement(QUERY_UPDATE);

			query.setString(1, companyName);
			query.setString(2, mobileNumber);
			query.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return 500;
		} finally {
			if (conn != null)
				conn.close();
		}

		return 200;
	}

	public int deleteItem(String mobileNumber) throws Exception {

		PreparedStatement query = null;
		Connection conn = null;

		try {
			conn = getOracleConn();
			query = conn.prepareStatement(QUERY_DELETE);

			query.setString(1, mobileNumber);
			query.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return 500;
		} finally {
			if (conn != null)
				conn.close();
		}

		return 200;
	}

}
