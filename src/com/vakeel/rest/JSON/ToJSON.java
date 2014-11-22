package com.vakeel.rest.JSON;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.EncodingException;

public class ToJSON {

	public JSONArray toJSonArray(ResultSet result) throws JSONException, EncodingException {

		JSONArray jsonArray = new JSONArray();
		String temp=null;
		try {
			ResultSetMetaData resMetaData = result.getMetaData();

			while (result.next()) {

				int numColums = resMetaData.getColumnCount();
				JSONObject jsObj = new JSONObject();

				for (int i = 1; i < numColums + 1; i++) {
					String columName = resMetaData.getColumnName(i);
					if (resMetaData.getColumnType(i) == java.sql.Types.VARCHAR) {
						
						temp=result.getString(columName);
						temp=ESAPI.encoder().canonicalize(temp);
						temp=ESAPI.encoder().encodeForHTML(temp);
						jsObj.put(columName, temp);
					}
				}
				jsonArray.put(jsObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
}
