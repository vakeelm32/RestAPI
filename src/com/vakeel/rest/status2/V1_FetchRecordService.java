package com.vakeel.rest.status2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.owasp.esapi.errors.EncodingException;

import com.vakeel.rest.JSON.ToJSON;
import com.vakeel.rest.dao.OracleDao;

@Path("/fetchRecord")
public class V1_FetchRecordService {

	@Path("friends")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getFriendsData() {
		String query = "select *from RESTAPI_TEST";
		PreparedStatement stmnt = null;
		ResultSet result = null;
		Connection conn = null;
		String resonse = null;

		try {
			conn = OracleDao.getOracleConn();
			stmnt = conn.prepareStatement(query);
			result = stmnt.executeQuery();

			ToJSON objConvert = new ToJSON();
			JSONArray jsArray = new JSONArray();

			jsArray = objConvert.toJSonArray(result);
			resonse = jsArray.toString();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (EncodingException e) {
			e.printStackTrace();
		}
		return resonse;
	}
}
