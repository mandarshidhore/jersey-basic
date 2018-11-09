package com.sssm.jerseybasic.controller;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import com.sssm.jerseybasic.dao.AthleteRepo;
import com.sssm.jerseybasic.exception.DataNotFoundException;
import com.sssm.jerseybasic.model.Athlete;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResourceController {
	
	AthleteRepo repo = new AthleteRepo();

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	@GET
	@Path("getAthlete")
	@Produces(MediaType.APPLICATION_XML)
	public Athlete getAthlete() {
		Athlete athlete = new Athlete();
		athlete.setAge(10);
		athlete.setName("Athlete 1");
		return athlete;
	}
	
	// return Response object that returns status and created object as entity.
	@GET
	@Path("getAthleteInResponseObject")
	public Response getAthleteInResponseObject(@Context UriInfo uriInfo) {
		Athlete athlete = new Athlete();
		athlete.setAge(10);
		athlete.setName("Athlete 1");
		// below is one way of returning response
		// return Response.status(Status.CREATED).entity(athlete).build();

		// other way of returning response is using UriInfo object to get location
		URI uri = uriInfo.getAbsolutePathBuilder().path("Athlete 1").build();
		return Response.created(uri).entity(athlete).build();
	}

	@GET
	@Path("getAthlete/{id}")
	// use {} inside @Produces to return multiple media types 
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Athlete getAthleteByID(@PathParam("id") final int age) {
		System.out.println("Age = " + age);
		Athlete athlete = new Athlete();
		athlete.setAge(age);
		athlete.setName("Athlete 4");
		return athlete;
	}

	// if json-binding dependency is absent, an exception will be thrown
	// org.glassfish.jersey.message.internal.WriterInterceptorExecutor$TerminalWriterInterceptor.aroundWriteToMessageBodyWriter not found for media type=application/json
	@GET
	@Path("getAllAthletes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Athlete> getAllAthletes() {
		// dummy-data implementation
		/*Athlete a1 = new Athlete();
		a1.setAge(10);
		a1.setName("Athlete 1");
		Athlete a2 = new Athlete();
		a2.setAge(20);
		a2.setName("Athlete 2");
		return Arrays.asList(a1, a2);*/
		return repo.getAllAthletes();
	}

	@POST
	@Path("addAthlete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Athlete addAthlete(Athlete a) {
		System.out.println(a.toString());
		return a;
	}
	
	// redirect to sub resource controller
	@Path("getAthlete/sub/{subid}")
	public MyResourceSubController getAthleteSub() {
		return new MyResourceSubController();
	}
	
	// demonstrate exception handling
	@GET
	@Path("/throwexception")
	public String getException() {
		throw new DataNotFoundException("Throwing DataNotFoundException...");
	}

}
