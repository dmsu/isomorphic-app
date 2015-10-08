package com.turbo.rest;

import static java.util.Arrays.asList;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.turbo.model.Class;

@Path("classes")
@Produces("application/json")
public class ClassResource {

	@GET
	public List<Class> getAllClasses() {
		return asList(new Class(0, 0, "root"), new Class(1, 0, "entity"));
	}
}
