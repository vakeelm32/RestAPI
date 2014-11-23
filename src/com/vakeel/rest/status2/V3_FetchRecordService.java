package com.vakeel.rest.status2;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.vakeel.rest.dao.QueryResult;

@Path("/v3/records")
public class V3_FetchRecordService {

	@Path("postRequest")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFreindsRecord(String incomingData) throws Exception {

		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		try {
			System.out.println("incomingData: " + incomingData);

			JSONObject itemEntry = new JSONObject(incomingData);
			System.out.println("jsonData: " + itemEntry.toString());
			QueryResult queryResult = new QueryResult();
			int http_code = queryResult.insertIntoPC_PARTS(
					itemEntry.optString("name"),
					itemEntry.optString("interest"),
					itemEntry.optString("developer"),
					itemEntry.optString("ctc"),
					itemEntry.optString("hometown"),
					itemEntry.optString("company"),
					itemEntry.optString("position"),
					itemEntry.optString("technology"),
					itemEntry.optString("phonenumber"));

			if (http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG",
						"Item has been entered successfully, Version 3");
				returnString = jsonArray.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Unable to enter Item")
						.build();
			}

			System.out.println("returnString: " + returnString);

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your request")
					.build();
		}

		return Response.ok(returnString).build();
	}
	
	@Path("update/{mobileNumber}/{name}")
	@PUT
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateItem(@PathParam("mobileNumber") String mobileNumber,
									@PathParam("name") String companyName,
									String incomingData) 
								throws Exception {
		
		int http_code;
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		QueryResult queryResult=new QueryResult();
		
		try {
			
			http_code = queryResult.updateItem(companyName, mobileNumber);
			
			if(http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been updated successfully");
			} else {
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			
			returnString = jsonArray.put(jsonObject).toString();
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	@Path("delete/{mobileNumber}/{name}")
	@DELETE
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItem(@PathParam("mobileNumber") String mobileNumber,
									@PathParam("name") String name,
									String incomingData) 
								throws Exception {
		
		//System.out.println("incomingData: " + incomingData);
		//System.out.println("brand: " + brand);
		//System.out.println("item_number: " + item_number);
		
		int http_code;
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		QueryResult queryResult=new QueryResult();
		
		try {
			
			http_code = queryResult.deleteItem(mobileNumber);
			
			if(http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been deleted successfully");
			} else {
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			
			returnString = jsonArray.put(jsonObject).toString();
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
}
