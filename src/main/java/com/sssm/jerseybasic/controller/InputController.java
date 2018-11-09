package com.sssm.jerseybasic.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

@Path("/getInfo")
public class InputController {

	@GET
	@Path("/getURIData/{pathparam1}/{pathparam2}")
	public String getURIData(@Context UriInfo uriInfo) {
		return "UriInfo data\nabsolute path = " + uriInfo.getAbsolutePath() + "\npath params = "
				+ uriInfo.getPathParameters().values().toString();
	}

	@GET
	@Path("/getHeaderData")
	public String getHeaderData(@Context HttpHeaders headers) {
		return "Header values = " + headers.getRequestHeaders().values().toString();
	}

}
