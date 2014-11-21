package com.vakeel.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("first/app")
public class FirstService {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String myName(){
		return "My Name is Vakeel";
	}
}
