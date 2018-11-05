package com.sssm.jerseybasic.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sssm.jerseybasic.dao.AthleteRepo;
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

}
