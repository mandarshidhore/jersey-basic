package com.sssm.jerseybasic.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Sub resource
 */
@Path("/") // this is optional for sub resource
public class MyResourceSubController {

	@GET
	public String getSub() {
		return "SubResource";
	}

	@GET
	@Path("/{subname}")
	public String getSubWithName(@PathParam("subid") String subid, @PathParam("subname") String subname) {
		return "SubResource Data\nsubid = " + subid + "\nsubname = " + subname;
	}

}
