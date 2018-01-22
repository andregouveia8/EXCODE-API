package com.excode.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.excode.imp.ErrorManager;
import com.excode.model.Failure;

@Path("/errors")
public class ErrorsResource {

	// GET ALL USERS
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Failure> getErrors() {

		ErrorManager errorManager = ErrorManager.getInstance();
		return errorManager.getErrors();

	}

	// GET ALL USERS
	@Path("/{errorID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Failure> getErrorsID(@PathParam("errorID") int errorID) {

		ErrorManager errorManager = ErrorManager.getInstance();
		return errorManager.getErrorID(errorID);

	}

}
