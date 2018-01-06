package com.excode.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.excode.imp.UserManager;
import com.excode.model.User;

@Path("/users")
public class UsersResource {

	UserManager userManager;

	// URI REQUESTS JSON (APPLICATION_JSON)
	// *************************************************************************

	// GET ALL USERS
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsersJSON() {

		userManager = UserManager.getInstance();
		return userManager.getUsers();

	}

	// GET A SPECIFIC USER
	@Path("/{userID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserJSON(@PathParam("userID") String userID) {

		UserManager um = UserManager.getInstance();
		return um.getUser(userID);
	}

	// URI REQUESTS XML (APPLICATION_XML)
	// *************************************************************************

	// GET ALL USERS
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<User> getUsersXML() {

		UserManager userManager = UserManager.getInstance();
		return userManager.getUsers();

	}

	// GET A SPECIFIC USER
	@Path("/{userID}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public User getUserXML(@PathParam("userID") String userID) {

		return null;
	}

	// ANOTHER URI REQUESTS
	// *************************************************************************

	// DELETE A SPECIFIC USER
	@Path("/{userID}")
	@DELETE
	public Response removeUser(@PathParam("userID") String userID) {
		UserManager userManager = UserManager.getInstance();
		userManager.removeUser(userID);
		return Response.ok().entity("User removed!").build();

	}

	// POST A NEW USER
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response insertUser(@FormParam("userID") String userID, @FormParam("email") String email,
			@FormParam("password") String password, @FormParam("nameUser") String nameUser, @Context UriInfo uriInfo) {

		UserManager userManager = UserManager.getInstance();
		userManager.createUser(userID, email, password, nameUser);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(userID);
		return Response.created(builder.build()).build();
	}

	// // PUT A SPECIFIC USER
	// @PUT
	// @Consumes("application/x-www-form-urlencoded")
	// public Response updateUser(@FormParam("userID") String userID,
	// @FormParam("email") String email,
	// @FormParam("password") String password, @FormParam("nameUser") String
	// nameUser, @Context UriInfo uriInfo) {
	//
	// return null;
	// }

}
