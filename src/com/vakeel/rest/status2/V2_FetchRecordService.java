package com.vakeel.rest.status2;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.owasp.esapi.errors.EncodingException;

import com.vakeel.rest.constant.RecordBean;
import com.vakeel.rest.dao.QueryResult;

@Path("/v2/records")
public class V2_FetchRecordService {

	@Path("allRecord")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFriendsData() {
		JSONArray jsonArray = null;
		try {
			jsonArray = QueryResult.getAllRecords();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EncodingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Response.ok(jsonArray.toString()).build();

	}

	@Path("mobileRecord")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMobileFriendsData(
			@QueryParam("mobileNumber") String mobileNumber) {
		JSONArray jsonArray = null;
		
		if (mobileNumber == null) {
			return Response.status(400).entity("Error: please specify mobileNumber for this search").build();
		}
		
		try {
			jsonArray = QueryResult.getMobileRecord(mobileNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(jsonArray.toString()).build();

	}
	
	@Path("postRequest")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFreindsRecord(String incomingData) throws Exception {
		
		String returnString = null;
		
		try {
			System.out.println("incomingData: " + incomingData);
			JSONObject partsData = new JSONObject();
			ObjectMapper mapper=new ObjectMapper();
			JSONArray jsonArray = new JSONArray();
			RecordBean itemEntry = mapper.readValue(incomingData,RecordBean.class);
			
			QueryResult queryResult=new QueryResult();
			int http_code = queryResult.insertIntoPC_PARTS(itemEntry.getName(),
													itemEntry.getInterest(), 
													itemEntry.getDeveloper(),
													itemEntry.getCtc(), 
													itemEntry.getHometown(),
													itemEntry.getCompany(),
													itemEntry.getPostion(),
													itemEntry.getTechnology(),
													itemEntry.getPhonenumber()
													);
			
			if( http_code == 200 ) {
				//returnString = jsonArray.toString();
				returnString = "Item inserted";
				partsData.put("MSG", returnString);
				returnString = jsonArray.put(partsData).toString();
				
			} else {
				return Response.status(500).entity("Unable to process Item").build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
}
